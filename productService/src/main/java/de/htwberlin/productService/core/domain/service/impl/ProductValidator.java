package de.htwberlin.productService.core.domain.service.impl;

import de.htwberlin.productService.core.domain.model.Product;

public class ProductValidator {

    public static void validate(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");

        if (product.getProductId() == null)
            throw new IllegalArgumentException("Product ID cannot be null.");

        if (product.getAmount() < 0)
            throw new IllegalArgumentException("Product amount must be a positive number.");
    }
}
