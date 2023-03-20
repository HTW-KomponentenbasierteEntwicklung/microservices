package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentWithOrderIdNotFoundException;
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
    public Payment createPayment(Payment payment) {
        // payment.setStatus(PaymentStatus.SUCCESS); // Todo: hier richtig? Und noch Fehler abfangen?
        return paymentRepository.save(payment);
    }

    @Override   // Todo: Warum update Status? Es müsste doch updatePayment sein! Der status wird über payment.setStatus festgelegt
    public Payment updatePaymentStatus(UUID orderId, PaymentStatus status) throws PaymentWithOrderIdNotFoundException {
        Payment existingPayment = paymentRepository.findByOrderNr(orderId);
        if (existingPayment == null){
            throw new PaymentWithOrderIdNotFoundException();
        }
        existingPayment.setStatus(status);
        return paymentRepository.save(existingPayment);
    }

    @Override
    public Payment getPaymentByOrderId(UUID orderId) throws PaymentWithOrderIdNotFoundException {
        Payment paymentByOrderId = paymentRepository.findByOrderNr(orderId);
        if (paymentByOrderId == null){
            throw new PaymentWithOrderIdNotFoundException();
        }else{
            return paymentByOrderId;
        }
    }
}
