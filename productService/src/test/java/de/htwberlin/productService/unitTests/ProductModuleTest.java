package de.htwberlin.productService.unitTests;

import de.htwberlin.productService.core.domain.service.impl.ProductService;
import de.htwberlin.productService.core.domain.service.interfaces.IProductRepository;
import de.htwberlin.productService.port.product.user.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductModuleTest {

/*    @Autowired
    private ProductController productController;

    @Autowired
    private ProductService productService;

    @Autowired
    private IProductRepository productRepository;

    @Test
    void productModuleIsLoaded() {
        assertThat(productController).isNotNull();
        assertThat(productService).isNotNull();
        assertThat(productRepository).isNotNull();
    }*/
}
