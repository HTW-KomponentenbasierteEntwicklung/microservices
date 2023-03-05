package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentWithOrderIdNotFoundException;
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
    public Payment updatePaymentStatus(UUID orderId, PaymentStatus status) throws PaymentWithOrderIdNotFoundException {
        List<Payment> existingPayment = paymentRepository.findByOrderNr(orderId);
        if (existingPayment.size() == 0){
            throw new PaymentWithOrderIdNotFoundException();
        }
        existingPayment.get(0).setStatus(status);
        return paymentRepository.save(existingPayment.get(0));
    }

    @Override
    public Payment getPaymentByOrderId(UUID orderId) throws PaymentWithOrderIdNotFoundException {
        List<Payment> paymentByOrderId = paymentRepository.findByOrderNr(orderId);
        if (paymentByOrderId.size() == 0){
            throw new PaymentWithOrderIdNotFoundException();
        }else{
            return paymentByOrderId.get(0);
        }

    }




}
