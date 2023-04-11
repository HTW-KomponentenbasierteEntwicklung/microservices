package de.htwberlin.cartService.port.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class ProductChangeDTO {
    UUID id;
    int changeAmount;

}
