package de.htwberlin.cartService.port.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ProductChangeDTO {
    UUID id;
    int changeAmount;

}
