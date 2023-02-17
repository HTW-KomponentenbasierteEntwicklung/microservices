package de.htwberlin.paymentService.core.domain.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentEmailDTO {
    String username;
    BigDecimal amount;
    String orderNr;

}