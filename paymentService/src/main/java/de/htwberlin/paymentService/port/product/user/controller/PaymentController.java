package de.htwberlin.paymentService.port.product.user.controller;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.user.exception.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping(path = "/payment")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Payment create(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPaymentById(@PathVariable UUID id) throws PaymentNotFoundException {
        return paymentService.getPaymentById(id);
    }

    @PutMapping(path="/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Payment update (@PathVariable("id") UUID id, @RequestBody Payment payment) throws PaymentNotFoundException {
        return paymentService.updateProduct(id, payment);
    }


}
