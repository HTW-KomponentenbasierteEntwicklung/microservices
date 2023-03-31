package de.htwberlin.paymentService;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.NoPaymentsWithOrderIdFoundException;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentIdNotFoundException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.user.controller.PaymentController;
import de.htwberlin.paymentService.port.product.user.producer.PaymentProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTests {

    @Mock
    private IPaymentService paymentService;

    @Mock
    private PaymentProducer paymentProducer;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    public void testCreatePayment() {
        Payment payment = new Payment();
        payment.setOrderId(UUID.randomUUID());
        payment.setAmount(BigDecimal.TEN);

        when(paymentService.createPayment(payment)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.create(payment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
    }

    @Test
    public void testCreatePaymentWithInvalidRequest() {
        Payment payment = null;

        when(paymentService.createPayment(payment)).thenThrow(new IllegalArgumentException("Payment cannot be null."));

        ResponseEntity<Payment> response = paymentController.create(payment);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPaymentsByOrderId() {
        UUID orderId = UUID.randomUUID();
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        List<Payment> payments = Arrays.asList(payment);

        when(paymentService.getPaymentsByOrderId(orderId)).thenReturn(payments);

        ResponseEntity<List<Payment>> response = paymentController.getPaymentsByOrderId(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payments, response.getBody());
    }

    @Test
    public void testGetPaymentsByOrderIdWithInvalidOrderId() {
        UUID orderId = null;

        when(paymentService.getPaymentsByOrderId(orderId)).thenThrow(new IllegalArgumentException("Order ID is invalid."));

        ResponseEntity<List<Payment>> response = paymentController.getPaymentsByOrderId(orderId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetPaymentsByOrderIdWithNoPaymentsFound() {
        UUID orderId = UUID.randomUUID();

        when(paymentService.getPaymentsByOrderId(orderId)).thenThrow(new NoPaymentsWithOrderIdFoundException(orderId));

        ResponseEntity<List<Payment>> response = paymentController.getPaymentsByOrderId(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdatePaymentStatus() throws Exception {
        UUID paymentId = UUID.randomUUID();
        Payment payment = new Payment();
        payment.setId(paymentId);

        when(paymentService.updatePaymentStatus(paymentId, PaymentStatus.SUCCESS)).thenReturn(payment);
        doNothing().when(paymentProducer).sendMessageToEmailService(payment);

        ResponseEntity<Payment> response = paymentController.updatePaymentStatus(paymentId, PaymentStatus.SUCCESS); //Todo: anderen Status?

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
        verify(paymentProducer).sendMessageToEmailService(payment);
    }

    @Test
    public void testUpdatePaymentStatusWithInvalidPaymentId() {
        UUID paymentId = null;

        when(paymentService.updatePaymentStatus(paymentId, PaymentStatus.SUCCESS)).thenThrow(new IllegalArgumentException("Payment ID is missing or invalid"));

        ResponseEntity<Payment> response = paymentController.updatePaymentStatus(paymentId, PaymentStatus.SUCCESS); //Todo: anderen Status?

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdatePaymentStatusWithPaymentIdNotFound() {
        UUID paymentId = UUID.randomUUID();

        when(paymentService.updatePaymentStatus(paymentId, PaymentStatus.SUCCESS)).thenThrow(new PaymentIdNotFoundException(paymentId));

        ResponseEntity<Payment> response = paymentController.updatePaymentStatus(paymentId, PaymentStatus.SUCCESS); //Todo: anderen Status?

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}





