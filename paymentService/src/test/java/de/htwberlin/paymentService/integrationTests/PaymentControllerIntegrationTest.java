package de.htwberlin.paymentService.integrationTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.paymentService.PaymentServiceApplication;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;



import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaymentServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PaymentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Test
    void getPaymentsByOrderId_ValidOrderId_ReturnsListOfPayments() throws Exception {
        UUID orderId = UUID.randomUUID();

        // create a payment object and persist it to the database
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        paymentRepository.save(payment);

        // perform a GET request to the endpoint
        mockMvc.perform(get("/payment/{orderId}", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].orderId", is(orderId.toString())));
    }






/*
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IPaymentRepository paymentRepository;


    @Test
    public void getPaymentsByOrderIdValidTest() throws Exception {
        UUID orderId = UUID.randomUUID();
        Payment payment = new Payment(orderId, "username", BigDecimal.ONE, PaymentStatus.PENDING, null);   //TODO: Warum immer der geliche status?

        List<Payment> payments = Arrays.asList(payment);
        paymentRepository.save(payment);

        MvcResult mvcResult = mvc.perform(get("/payment/" + orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        List<Payment> actualPayments = objectMapper.readValue(responseContent, new TypeReference<List<Payment>>() {});
        assertEquals(payments, actualPayments);
    }


    @Test
    public void getPaymentsByOrderIdValidTest_v2() {
        UUID orderId = UUID.randomUUID();
        Payment payment = new Payment(orderId, "username", BigDecimal.ONE, PaymentStatus.PENDING, null);   //TODO: Warum immer der geliche status?
        paymentRepository.save(payment);

        ResponseEntity<List<Payment>> response = restTemplate.exchange(
                "/payments/" + orderId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Payment>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Payment> payments = response.getBody();
        assertNotNull(payments);
        assertEquals(1, payments.size());
        assertEquals(orderId, payments.get(0).getOrderId());
    }

*/


/*
    @Autowired
    private MockMvc mvc;

    @Autowired
    private IPaymentRepository paymentRepository;

    @Test
    public void getPaymentsByOrderIdValid () {
        UUID orderId = UUID.randomUUID();
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        List<Payment> payments = Arrays.asList(payment);

        mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is(payments)));
    }*/
}
