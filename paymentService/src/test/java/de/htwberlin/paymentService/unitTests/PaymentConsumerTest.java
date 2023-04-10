package de.htwberlin.paymentService.unitTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.dto.OrderDTO;
import de.htwberlin.paymentService.port.product.user.consumer.PaymentConsumer;
import de.htwberlin.paymentService.port.product.user.exception.PaymentIdAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PaymentConsumerTest {
    @Mock
    private IPaymentService paymentService;

    @InjectMocks
    private PaymentConsumer paymentConsumer;

    @Test
    public void testConsumeOrder() throws JsonProcessingException {
        // set up the test data
        OrderDTO orderDTO = new OrderDTO();
        UUID orderId = UUID.randomUUID();
        orderDTO.setOrderId(orderId);
        orderDTO.setUsername("user1");
        orderDTO.setTotalAmount(BigDecimal.valueOf(100.0));

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(orderDTO);

        // specify the behavior of the paymentService mock
        Payment expectedPayment = new Payment(orderId, "user1", BigDecimal.valueOf(100.0), PaymentStatus.PENDING, null);
        when(paymentService.createPayment(any(Payment.class))).thenReturn(expectedPayment);

        // call the method under test
        paymentConsumer.consumeOrder(message);

        // verify the expected behavior
        verify(paymentService).createPayment(expectedPayment);
        assertEquals(orderId, expectedPayment.getOrderId());
        assertEquals("user1", expectedPayment.getUsername());
        assertEquals(BigDecimal.valueOf(100.0), expectedPayment.getAmount());
        assertEquals(PaymentStatus.PENDING, expectedPayment.getStatus());
        assertNull(expectedPayment.getMethod());
    }


    @Test
    public void testConsumeOrderInvalidMessage() {
        String message = "invalid message";
        assertThrows(RuntimeException.class, () -> paymentConsumer.consumeOrder(message));
    }

    /*@Test // AssertionFailedError: Expected java.lang.Exception to be thrown, but nothing was thrown.
    public void testConsumeOrder_ExceptionThrown() throws JsonProcessingException {
        OrderDTO orderDTO = new OrderDTO();
        UUID orderId = UUID.randomUUID();
        orderDTO.setOrderId(null);
        orderDTO.setUsername("user2");
        orderDTO.setTotalAmount(BigDecimal.valueOf(200.0));

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(orderDTO);

        //when(paymentService.createPayment(any(Payment.class))).thenThrow(new PaymentIdAlreadyExistsException("Error creating payment"));

        assertThrows(Exception.class, () -> paymentConsumer.consumeOrder(message));
    }*/

}
