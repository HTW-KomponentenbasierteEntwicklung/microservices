package de.htwberlin.paymentService.port.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentEmailDTO {
    String username;
    BigDecimal amount;
    UUID orderNr;

}