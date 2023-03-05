package de.htwberlin.orderService.port.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderDTO {
    @Getter @Setter
    private UUID orderNr;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private BigDecimal totalAmount;
    @Getter @Setter
    private String email;
}
