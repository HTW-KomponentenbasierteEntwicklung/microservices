package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class PaymentServiceIntegrationTests {

    @TestConfiguration
    static class PaymentServiceTestContextConfiguration {

        @Bean
        public IPaymentService paymentService() {
            return new PaymentService();
        }
    }

    @Autowired
    private IPaymentService paymentService;

    @MockBean
    private IPaymentRepository paymentRepository;

    @Before
    public void setUp() {
        Payment payment = new Payment();

        Mockito.when(paymentRepository.findByOrderNr(payment.getOrderNr()))
                .thenReturn(payment);
    }

    // write Test cases here
}
