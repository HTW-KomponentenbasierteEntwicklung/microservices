package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentMethod;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
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

    @Test
    public void createValidPaymentTest() {
        when(paymentRepository.findAll()).thenReturn(anyList());
        Payment paymentRequest = new Payment(UUID.randomUUID(), "test", BigDecimal.valueOf(100), PaymentStatus.FAILED, PaymentMethod.VORKASSE); // Todo: Muss in einer Datenbank angelegt werden, sonst ist UUID immer null
        Payment paymentCreated = paymentService.createPayment(paymentRequest);
        assertThat(paymentCreated.getId()).isSameAs(paymentRequest.getId());
    }
}
