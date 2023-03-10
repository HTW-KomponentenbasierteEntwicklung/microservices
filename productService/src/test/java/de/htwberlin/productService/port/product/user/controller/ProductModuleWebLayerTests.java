package de.htwberlin.productService.port.product.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.assertj.core.api.Assertions.*;

@WebMvcTest
public class ProductModuleWebLayerTests {

    @Autowired
    private ProductController productController;

    @Test
    void productControllerIsLoaded() {
        assertThat(productController).isNotNull();
    }

}
