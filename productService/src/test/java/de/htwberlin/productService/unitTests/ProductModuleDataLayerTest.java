package de.htwberlin.productService.unitTests;

import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class ProductModuleDataLayerTest {

    @Autowired
    private IProductRepository productRepository;
/*
    @Test
    void productRepositoryIsLoaded() {
        assertThat(productRepository).isNotNull();
    }

    @Test
    void versionTest() {
        Product product = new Product();
        product.setAmount(100);
        productRepository.save(product);
    }*/
}
