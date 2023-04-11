package de.htwberlin.productService.port.product.user.exception;

import java.util.UUID;

public class ProductIdAlreadyExistsException extends IllegalArgumentException {
    public ProductIdAlreadyExistsException(UUID id) {
        super("Payment ID already exists: " + id);
    }
}
