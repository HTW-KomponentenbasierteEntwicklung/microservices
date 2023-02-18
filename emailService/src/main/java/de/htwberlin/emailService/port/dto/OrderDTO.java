package de.htwberlin.emailService.port.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class OrderDTO {

    private UUID orderNr;
    private String username;
    private BigDecimal totalAmount;
    private String email;
}
