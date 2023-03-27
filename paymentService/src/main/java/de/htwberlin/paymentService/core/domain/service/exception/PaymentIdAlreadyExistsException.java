package de.htwberlin.paymentService.core.domain.service.exception;

import java.util.UUID;

public class PaymentIdAlreadyExistsException extends IllegalArgumentException {
    public PaymentIdAlreadyExistsException(UUID paymentId) {
        super("Payment ID already exists: " + paymentId);
    }
}
