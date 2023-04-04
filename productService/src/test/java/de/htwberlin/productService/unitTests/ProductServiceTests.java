package de.htwberlin.productService.unitTests;

import static org.junit.jupiter.api.Assertions.*;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Color;
import de.htwberlin.productService.core.domain.model.Material;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductRepository;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ProductServiceTests {

    /*

    private IProductRepository productRepository;

    private IProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(IProductRepository.class);
        productService = new ProductService(productRepository);
    }

    @Nested
    @DisplayName("All the test cases for creating a product")
    class CreateProductTests {

        @Test
        void createProductValidTest() {
            Product product = new Product("Wal", "", new BigDecimal(1), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", 1);
            product.setAmount(100);
            assertEquals(100, product.getAmount());
            assertEquals("Wal", product.getName());
        }

        @Test
        void createProductNullTest() {
            Product product = null;
            assertThrows(NullPointerException.class, () -> productService.createProduct(product));
        }

        @Test
        void createProductNegativePriceTest() {
            Product product = new Product("Wal", "", new BigDecimal(-100), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", 1);
            assertThrows(Exception.class, () -> productService.createProduct(product));
        }

        @Test
        void createProductNegativeAmountTest() {
            Product product = new Product("Wal", "", new BigDecimal(1), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", -100);
            assertThrows(Exception.class, () -> productService.createProduct(product));
            assertThrows(Exception.class, () -> product.setAmount(-200));
        }

        // https://github.com/greenlearner01/microservices-projects-store/blob/master/Online-Shopping-Portal/product/src/test/java/com/greenlearner/product/service/ProductServiceTest.java
    }



    @Test
    void updateProductAmount() {

    }

    //public Product createProduct(Product product) {
    //        return productRepository.save(product);
    //    }


     */

}
