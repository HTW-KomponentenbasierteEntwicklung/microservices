package de.htwberlin.productService.unitTests;

import de.htwberlin.productService.core.domain.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTests {

    @Test
    void setAmountTest() {
        Product product = new Product();
        product.setAmount(100);
        assertEquals(100, product.getAmount());
    }
}
