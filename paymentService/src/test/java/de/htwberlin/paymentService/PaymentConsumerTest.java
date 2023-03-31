package de.htwberlin.paymentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.dto.OrderDTO;
import de.htwberlin.paymentService.port.product.user.consumer.PaymentConsumer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentConsumerTest {
    @Mock
    private IPaymentService paymentService;

    private PaymentConsumer paymentConsumer;

    @Before
    public void setup() {
        paymentConsumer = new PaymentConsumer();
        paymentConsumer.paymentService = paymentService;
    }

    @Test
    public void testConsumeOrder() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        UUID orderId = UUID.randomUUID();
        orderDTO.setOrderId(orderId);
        orderDTO.setUsername("user1");
        orderDTO.setTotalAmount(BigDecimal.valueOf(100.0));

        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(orderDTO);

        paymentConsumer.consumeOrder(message);

        Payment expectedPayment = new Payment(orderId, "user1", BigDecimal.valueOf(100.0), PaymentStatus.PENDING, null);
        Mockito.verify(paymentService).createPayment(expectedPayment);
    }


}
