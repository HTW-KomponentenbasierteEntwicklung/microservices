package de.htwberlin.productService.unitTests.core.domain.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Color;
import de.htwberlin.productService.core.domain.model.Material;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.impl.ProductService;
import de.htwberlin.productService.core.domain.service.interfaces.IProductRepository;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.user.exception.ProductIdAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    IProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void testCreateProduct() throws ProductIdAlreadyExistsException {
        Product product = new Product("Wal", "", new BigDecimal(1), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", 1);
        product.setProductId(UUID.randomUUID());
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        verify(productRepository).save(product);
        assertEquals(product, createdProduct);
    }

    @Test
    void testCreateProductWithInvalidProduct() {
        Product invalidProduct = new Product();
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(invalidProduct));
        verify(productRepository, never()).save(any());
    }

    @Test
    void createProductNullTest() {
        Product product = null;
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
    }


    @Test
    void createProductNegativePriceTest() {
        Product product = new Product("Wal", "", new BigDecimal(-100), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", 1);
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        verify(productRepository, never()).save(any());
    }

    @Test
    void createProductNegativeAmountTest() {
        Product product = new Product("Wal", "", new BigDecimal(1), "", Category.OCEAN, Material.BAUMWOLLE, Color.COLORFUL, "", -100);
        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
    }

    @Test
    void updateProductAmount() {

    }

}
