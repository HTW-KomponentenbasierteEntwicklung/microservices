package de.htwberlin.paymentService.core.domain.service.interfaces;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.dto.PaymentEmailDTO;

public interface IDTOMappingService {
    PaymentEmailDTO convertDataToDTO(Payment payment);
}
