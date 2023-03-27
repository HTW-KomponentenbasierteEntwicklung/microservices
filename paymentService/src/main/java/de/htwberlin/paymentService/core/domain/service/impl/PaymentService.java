package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.NoPaymentsWithOrderIdFoundException;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentIdAlreadyExistsException;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentIdNotFoundException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    @Autowired
    private final IPaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        PaymentValidator.validate(payment);

        if (paymentRepository.existsById(payment.getId()))
            throw new PaymentIdAlreadyExistsException(payment.getId());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePaymentStatus(UUID paymentId, PaymentStatus newPaymentStatus) throws PaymentIdNotFoundException {
        if (newPaymentStatus == null)
            throw new IllegalArgumentException("Payment status is missing or invalid");
        if (paymentId == null)
            throw new IllegalArgumentException("Payment ID is missing or invalid");

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentIdNotFoundException(paymentId));
        payment.setStatus(newPaymentStatus);
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByOrderId(UUID orderId) throws NoPaymentsWithOrderIdFoundException {

        if (orderId == null)
            throw new IllegalArgumentException();

        List<Payment> payments = paymentRepository.findByOrderId(orderId);

        if (payments.isEmpty())
            throw new NoPaymentsWithOrderIdFoundException(orderId);

        return payments;
    }
}
