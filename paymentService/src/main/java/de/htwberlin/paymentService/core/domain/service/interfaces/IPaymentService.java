package de.htwberlin.paymentService.core.domain.service.interfaces;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.IdNotFoundException;
import java.util.UUID;

public interface IPaymentService {

    Payment createPayment(Payment payment);

    Payment updatePaymentStatus(UUID id, PaymentStatus status) throws IdNotFoundException;

    Payment getPaymentByOrderId(UUID orderId) throws IdNotFoundException;

}
