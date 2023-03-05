package de.htwberlin.productService.port.product.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductChangeDTO {
    private int difference;
    private UUID productId;
}
