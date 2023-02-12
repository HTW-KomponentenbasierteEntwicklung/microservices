package de.htwberlin.paymentService.port.product.user.exception;

import java.util.UUID;

public class PaymentNotFoundException extends RuntimeException {


    public PaymentNotFoundException(UUID id) {
        super("Product not found with id " + id);
    }

    public PaymentNotFoundException(String name) {
        super("Product not found with name " + name);
    }
}
