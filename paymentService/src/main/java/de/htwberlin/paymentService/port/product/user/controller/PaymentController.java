package de.htwberlin.paymentService.port.product.user.controller;

import de.htwberlin.paymentService.PaymentServiceApplication;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentNotFoundServicesException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IDTOMappingService;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.core.domain.model.dto.PaymentEmailDTO;
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
    private IDTOMappingService dtoMappingService;

    @GetMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Payment> getAllPayments() {

        return paymentService.getAllPayments();
    }
    @PostMapping(path = "/payment")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Payment create(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPaymentById(@PathVariable UUID id) throws PaymentNotFoundException {
        Payment payment = null;
        try {
            payment = paymentService.getPaymentById(id);
        }catch(PaymentNotFoundServicesException e){
            throw new PaymentNotFoundException(id);
        }
           return payment;
    }

    @PutMapping(path="/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Payment update (@PathVariable("id") UUID id, @RequestBody Payment payment) throws PaymentNotFoundException {
        Payment updatedpayment = null;
        try {
            updatedpayment = paymentService.updateProduct(id, payment);
        }catch(PaymentNotFoundServicesException e){
            throw new PaymentNotFoundException(id);
        }
        PaymentEmailDTO paymentEmailDTO = dtoMappingService.convertDataToDTO(updatedpayment);
        paymentProducer.sendMessage("hallo");
        log.info(updatedpayment.getUsername());
        return updatedpayment;
    }


}
