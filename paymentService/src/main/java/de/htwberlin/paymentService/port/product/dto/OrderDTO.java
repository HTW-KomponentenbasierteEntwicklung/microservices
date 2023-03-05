package de.htwberlin.paymentService.port.product.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class OrderDTO {

    private UUID orderNr;
    private String username;
    private BigDecimal totalAmount;
    private String email;
}
