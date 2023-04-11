package de.htwberlin.paymentService.integrationTests;

import de.htwberlin.paymentService.PaymentServiceApplication;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.service.impl.PaymentService;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest(classes = PaymentServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PaymentServiceIntegrationTests {

    @TestConfiguration
    static class PaymentServiceTestContextConfiguration {

        @MockBean
        private IPaymentRepository paymentRepository;

        @Bean
        public IPaymentService paymentService() {
            return new PaymentService(paymentRepository) {   // Todo: Leeren Konstruktor in PaymentService?
                // implement methods
            };
        }
    }

    @Autowired
    private IPaymentService paymentService;

    @MockBean
    private IPaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        Payment payment = new Payment();
        List<Payment> payments = new LinkedList<>();

        Mockito.when(paymentRepository.findByOrderId(payment.getOrderId()))
                .thenReturn(payments);
    }

    // write Test cases here
}
