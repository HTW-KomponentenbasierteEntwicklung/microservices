package de.htwberlin.paymentService.port.product.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentWithOrderIdNotFoundException;
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

    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private PaymentProducer paymentProducer;

    @PostMapping(path = "/payment")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Payment create(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payment/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPaymentByOrderId(@PathVariable UUID orderID) throws PaymentNotFoundException {
        Payment payment = null;
        try {
            payment = paymentService.getPaymentByOrderId(orderID);
        }catch(PaymentWithOrderIdNotFoundException e){
            throw new PaymentNotFoundException(orderID);
        }
           return payment;
    }

    @PutMapping(path="/paymentsuccess/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Payment updatePaymentStatusSuccess (@PathVariable("orderid") UUID orderId) throws PaymentNotFoundException {
        Payment payment =paymentService.updatePaymentStatus(orderId, PaymentStatus.SUCCESS);
        paymentProducer.sendMessageToEmailService(payment);
        return payment;
    }


}
