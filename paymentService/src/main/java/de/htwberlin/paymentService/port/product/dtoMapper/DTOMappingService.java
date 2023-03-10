package de.htwberlin.paymentService.port.product.dtoMapper;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.port.product.dto.PaymentEmailDTO;
import org.springframework.stereotype.Service;

@Service
public class DTOMappingService{
    public PaymentEmailDTO convertPaymentToDTO(Payment payment) {
        PaymentEmailDTO dto = new PaymentEmailDTO();
        dto.setOrderNr(payment.getOrderNr());
        dto.setUsername(payment.getUsername());
        dto.setAmount(payment.getAmount());
        return dto;
    }
}
