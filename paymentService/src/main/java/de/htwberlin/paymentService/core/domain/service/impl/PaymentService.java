package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.IdNotFoundException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePaymentStatus(UUID id, PaymentStatus status) throws IdNotFoundException {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException());
        existingPayment.setStatus(status);
        return paymentRepository.save(existingPayment);
    }

    @Override
    public Payment getPaymentByOrderId(UUID orderId) throws IdNotFoundException {
        List<Payment> paymentByOrderId = paymentRepository.findByOrderNr(orderId);
        if (paymentByOrderId.size() == 0){
            throw new IdNotFoundException();
        }else{
            return paymentByOrderId.get(0);
        }

    }




}
