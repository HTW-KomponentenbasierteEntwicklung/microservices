package de.htwberlin.productService.integrationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.impl.ProductService;
import de.htwberlin.productService.port.product.user.controller.ProductController;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class)
public class ProductControllerTests {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    Product mockProduct = new Product();

    @Test
    void createProductTest() {

        Mockito.when(productService.createProduct(mockProduct)).thenReturn(mockProduct);
        // https://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services

    }*/

}
