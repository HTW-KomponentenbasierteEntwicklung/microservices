package de.htwberlin.paymentService.core.domain.service.impl;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.dto.PaymentEmailDTO;
import de.htwberlin.paymentService.core.domain.service.interfaces.IDTOMappingService;
import org.springframework.stereotype.Service;

@Service
public class DTOMappingService implements IDTOMappingService {
    @Override
    public PaymentEmailDTO convertDataToDTO(Payment payment) {
        PaymentEmailDTO dto = new PaymentEmailDTO();
        dto.setOrderNr(payment.getOrderNr());
        dto.setUsername(payment.getUsername());
        dto.setAmount(payment.getAmount());
        return dto;
    }
}
