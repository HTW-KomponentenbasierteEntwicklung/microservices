package de.htwberlin.productService.port.product.user.controller;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.user.exception.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    //private static final Logger log = LoggerFactory.getLogger(FinalWebshopApplication.class);   //Todo: Warum hier?

    @Autowired
    private IProductService productService;

    //Todo: ProductConsumer erstellen und bei den update Methoden einfügen (Siehe PaymentController in Paymenr Microservice)

    @PostMapping(path = "/product")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product createProduct(@RequestBody Product product) throws ProductIdAlreadyExistsException {
        return productService.createProduct(product);
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product getProductById(@PathVariable UUID productId)  throws ProductIdNotFoundException {
        return productService.getProductById(productId);
    }

    @DeleteMapping(path="/product")
    public void deleteProduct (@RequestBody UUID productId)  throws ProductIdNotFoundException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Product> getAllProducts() throws ProductsNotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("/product/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Product> findProductsByName(@PathVariable String name) throws ProductsNotFoundException {
        return productService.findProductsByName(name);
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Product> findProductsByCategory(@PathVariable Category category) throws ProductNotFoundException {
        return productService.findProductsByCategory(category);
    }

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product updateProductAmount(@PathVariable UUID productId, int difference) throws ProductIdNotFoundException {
        return productService.updateProductAmount(productId, difference);
    }
}
