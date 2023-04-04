package de.htwberlin.productService.port.product.user.exception;

import java.util.UUID;

public class ProductIdNotFoundException extends RuntimeException {
    public ProductIdNotFoundException(UUID id) {
        super("Product not found with id " + id);
    }

}
