package de.htwberlin.productService.core.domain.service.exception;

import java.util.UUID;

public class ProductIDNotFoundException extends RuntimeException {
    public ProductIDNotFoundException(UUID id) {
        super("Product not found with id " + id);
    }

}
