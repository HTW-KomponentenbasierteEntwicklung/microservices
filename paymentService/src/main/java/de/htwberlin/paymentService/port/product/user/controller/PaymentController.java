package de.htwberlin.paymentService.port.product.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.service.exception.IdNotFoundException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.dto.PaymentEmailDTO;
import de.htwberlin.paymentService.port.product.dtoMapper.DTOMappingService;
import de.htwberlin.paymentService.port.product.user.exception.PaymentNotFoundException;
import de.htwberlin.paymentService.port.product.user.producer.PaymentProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private PaymentProducer paymentProducer;
    @Autowired
    private DTOMappingService dtoMappingService;

    @PostMapping(path = "/payment")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Payment create(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payment/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPaymentById(@PathVariable UUID orderID) throws PaymentNotFoundException {
        Payment payment = null;
        try {
            payment = paymentService.getPaymentById(orderID);
        }catch(IdNotFoundException e){
            throw new PaymentNotFoundException(orderID);
        }
           return payment;
    }

    @PutMapping(path="/paymentsuccess/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Payment updatePaymentStatusSuccess (@PathVariable("id") UUID id) throws PaymentNotFoundException {
        Payment payment =paymentService.setPaymentStatusSuccess( id);
        PaymentEmailDTO paymentEmailDTO = dtoMappingService.convertPaymentToDTO(payment);
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(paymentEmailDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        paymentProducer.sendMessageToEmailService(message);
        log.info(payment.getUsername());
        return payment;
    }


}
