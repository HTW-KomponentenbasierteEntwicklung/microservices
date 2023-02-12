package de.htwberlin.finalWebshop.port.product.user.controller;

import de.htwberlin.finalWebshop.core.domain.service.interfaces.IProductService;
import jakarta.ws.rs.OPTIONS;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pd")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping(path = "/product")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody de.htwberlin.finalWebshop.core.domain.model.Payment create(@RequestBody de.htwberlin.finalWebshop.core.domain.model.Payment payment) {
        return productService.createProduct(payment);
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public de.htwberlin.finalWebshop.core.domain.model.Payment getProductById(@PathVariable UUID id) throws de.htwberlin.finalWebshop.port.product.user.exception.PaymentNotFoundException {
        return productService.getProductById(id);
    }

    @PutMapping(path="/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody de.htwberlin.finalWebshop.core.domain.model.Payment update (@PathVariable("id") UUID id, @RequestBody de.htwberlin.finalWebshop.core.domain.model.Payment payment) throws de.htwberlin.finalWebshop.port.product.user.exception.PaymentNotFoundException {
        return productService.updateProduct(id, payment);
    }

    @DeleteMapping(path="/product")
    public @ResponseBody void delete (@PathVariable UUID id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<de.htwberlin.finalWebshop.core.domain.model.Payment> getAllProducts() {

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
    List<de.htwberlin.finalWebshop.core.domain.model.Payment> findProductsByName(@PathVariable String name) {
        return productService.findProductsByName(name);
    }

    @GetMapping("/product/{sort}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<de.htwberlin.finalWebshop.core.domain.model.Payment> getAllProductsByFilter (@PathVariable Sort sort) {
        return productService.getAllProductsByFilter(sort);
    }

}
