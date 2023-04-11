package de.htwberlin.productService.port.product.user.exception;

import java.util.UUID;

public class ProductsNotFoundException  extends RuntimeException{
    public ProductsNotFoundException() {
        super("No products found.");
    }
}
