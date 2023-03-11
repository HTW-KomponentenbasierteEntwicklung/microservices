package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTests {

    @Mock
    IPaymentRepository paymentRepository;

    @InjectMocks
    PaymentService paymentService;

    @Test
    public void createEmptyPaymentTest() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(new Payment());
        Payment paymentRequest = new Payment();
        Payment paymentCreated = paymentService.createPayment(paymentRequest);
        assertThat(paymentCreated.getId()).isSameAs(paymentRequest.getId());
    }
}
