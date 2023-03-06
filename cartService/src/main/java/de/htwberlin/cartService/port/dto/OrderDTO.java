package de.htwberlin.cartService.port.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderDTO {

    private UUID orderId;
    private String username;
    private BigDecimal totalAmount;
    private String email;
}
