package de.htwberlin.productService.port.product.user.controller;

import de.htwberlin.productService.FinalWebshopApplication;
import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.exception.ProductIDNotFoundException;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.user.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(FinalWebshopApplication.class);

    @Autowired
    private IProductService productService;

    @PostMapping(path = "/product")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable UUID id) throws ProductNotFoundException {
        Product product = null;
        try {
            product = productService.getProductById(id);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @DeleteMapping(path="/product")
    public @ResponseBody void delete (@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Product> getAllProducts() {

        return productService.getAllProducts();
    }
    @GetMapping("/product/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Product> findProductsByName(@PathVariable String name) {
        return productService.findProductsByName(name);
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Product> findProductsByCategory(@PathVariable Category category) {
        return productService.findProductsByCategory(category);
    }


}
