package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.NoPaymentsWithOrderIdFoundException;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentIdAlreadyExistsException;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentIdNotFoundException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) throws PaymentIdAlreadyExistsException {
        PaymentValidator.validate(payment);

        if (paymentRepository.existsById(payment.getPaymentId()))
            throw new PaymentIdAlreadyExistsException(payment.getPaymentId());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) throws PaymentIdNotFoundException{
        if (payment == null)
            throw new IllegalArgumentException("Payment is missing or invalid");
        paymentRepository.findById(payment.getPaymentId())
                .orElseThrow(() -> new PaymentIdNotFoundException(payment.getPaymentId()));
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(UUID paymentId) {
        paymentRepository.deleteById(paymentId);
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
    public List<Payment> getPaymentsByOrderId(UUID orderId) {

        if (orderId == null)
            throw new IllegalArgumentException("Order ID is invalid.");

        List<Payment> payments = paymentRepository.findByOrderId(orderId);

        return payments;
    }
}
