package de.htwberlin.paymentService.port.product.user.exception;

import java.util.UUID;

public class PaymentWithOrderIdAlreadyExistsException extends IllegalArgumentException {
    public PaymentWithOrderIdAlreadyExistsException(UUID paymentId) {
        super("Payment ID already exists: " + paymentId);
    }
}
