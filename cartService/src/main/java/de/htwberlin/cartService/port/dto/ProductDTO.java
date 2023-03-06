package de.htwberlin.cartService.port.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ProductDTO {
    UUID id;
    int amount;

}
