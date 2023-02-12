package de.htwberlin.finalWebshop.port.product.user.controller;

import de.htwberlin.finalWebshop.core.domain.model.Product;
import de.htwberlin.finalWebshop.core.domain.service.interfaces.IProductService;
import de.htwberlin.finalWebshop.port.product.user.exception.ProductNotFoundException;
import jakarta.ws.rs.OPTIONS;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pd")
public class ProductController {

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
        return productService.getProductById(id);
    }

    @PutMapping(path="/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Product update (@PathVariable("id") UUID id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
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
    
    /*
    @PutMapping(path="/product/{id}")
    public void update(@PathVariable UUID id, @RequestBody Product product) {
        if(productService.findProductById(id).isPresent()) {
            Product oldProduct = productService.findProductById(id).get();
            oldProduct.setName(product.getName());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setPrice(product.getPrice());
            productService.createProduct(oldProduct);
        }
    }
    */

    @GetMapping("/product/{name}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<Product> findProductsByName(@PathVariable String name) {
        return productService.findProductsByName(name);
    }

    @GetMapping("/product/{sort}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Product> getAllProductsByFilter (@PathVariable Sort sort) {
        return productService.getAllProductsByFilter(sort);
    }

}
