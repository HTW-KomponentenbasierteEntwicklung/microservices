package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.PaymentServiceApplication;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.exception.PaymentNotFoundServicesException;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentRepository;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService implements IPaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentServiceApplication.class);

    private final IPaymentRepository paymentRepository;

    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(UUID id, Payment payment) throws PaymentNotFoundServicesException {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundServicesException());
        BeanUtils.copyProperties(payment, existingPayment, "id");
        log.info(payment.getAmount().toString());
        log.info(existingPayment.getAmount().toString());
        return paymentRepository.save(existingPayment);
    }

    @Override
    public Payment getPaymentById(UUID id) throws PaymentNotFoundServicesException {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundServicesException());
    }

    @Override
    public Iterable<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment setPaymentStatusSuccess(UUID id) {
        Payment payment = this.getPaymentById(id);
        payment.setStatus(PaymentStatus.SUCCESS);
        return this.updatePayment(id, payment);
    }

}
