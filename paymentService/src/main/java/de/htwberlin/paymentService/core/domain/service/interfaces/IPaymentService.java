package de.htwberlin.paymentService.core.domain.service.interfaces;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.NoPaymentsWithOrderIdFoundException;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentIdNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IPaymentService {

    Payment createPayment(Payment payment);

    Payment updatePaymentStatus(UUID paymentId, PaymentStatus status) throws PaymentIdNotFoundException;

    List<Payment> getPaymentsByOrderId(UUID orderId);

}
