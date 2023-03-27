package de.htwberlin.paymentService.core.domain.service.exception;

import java.util.UUID;

public class PaymentIdNotFoundException extends IllegalArgumentException {

    public PaymentIdNotFoundException(UUID paymentId) {
        super("There exists no payment with payment ID: " + paymentId);
    }
}
