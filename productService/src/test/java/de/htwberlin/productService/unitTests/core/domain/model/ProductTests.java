package de.htwberlin.productService.unitTests.core.domain.model;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Color;
import de.htwberlin.productService.core.domain.model.Material;
import de.htwberlin.productService.core.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductTests {
    @Test
    void createProductValidTest() {
        Product product = new Product("Wal", "", new BigDecimal(1), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", 1);
        product.setAmount(100);
        assertEquals(100, product.getAmount());
        assertEquals("Wal", product.getName());
    }
}
