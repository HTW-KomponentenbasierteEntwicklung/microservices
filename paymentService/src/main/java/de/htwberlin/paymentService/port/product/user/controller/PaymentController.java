package de.htwberlin.paymentService.port.product.user.controller;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.NoPaymentsWithOrderIdFoundException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.user.producer.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    private PaymentProducer paymentProducer;    //Todo: auch autowired annotation?

    @PostMapping(path = "/payment")
    public ResponseEntity<Payment> create(@RequestBody Payment payment) {
        Payment paymentResponse = paymentService.createPayment(payment);
        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/payment/{orderID}")
    public ResponseEntity<List<Payment>> getPaymentsByOrderId(@PathVariable UUID orderId) {

        List<Payment> payments = paymentService.getPaymentsByOrderId(orderId);
        if (payments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(payments);
        }
        /* meine alte Version
        try {
            List<Payment> payments = paymentService.getPaymentsByOrderId(orderId);
            return ResponseEntity.ok(payments);
        } catch (NoPaymentsWithOrderIdFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
         */
    }

    @PutMapping(path="/paymentStatus/{paymentId}")
    public ResponseEntity<Payment> updatePaymentStatus (@PathVariable("paymentId") UUID paymentId, @RequestBody PaymentStatus newStatus) {

        Payment payment = paymentService.updatePaymentStatus(paymentId, newStatus);
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            paymentProducer.sendMessageToEmailService(payment);
            return ResponseEntity.ok(payment);
        }


        /* meine alte Version
        try {
            Payment payment = paymentService.updatePaymentStatus(paymentId, newStatus);
            if (payment == null) {
                return ResponseEntity.notFound().build();
            } else {
                paymentProducer.sendMessageToEmailService(payment);
                return ResponseEntity.ok(payment);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

         */
    }
}
