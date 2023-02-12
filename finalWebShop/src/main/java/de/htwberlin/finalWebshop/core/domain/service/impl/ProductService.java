package de.htwberlin.finalWebshop.core.domain.service.impl;

import de.htwberlin.finalWebshop.core.domain.model.Product;
import de.htwberlin.finalWebshop.core.domain.service.interfaces.IProductRepository;
import de.htwberlin.finalWebshop.core.domain.service.interfaces.IProductService;
import de.htwberlin.finalWebshop.port.product.user.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UUID id, Product product) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        BeanUtils.copyProperties(product, existingProduct, "id");
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll(); //Hinweis: kann auch null zurückgeben
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findProductsByName(name);

    }

    @Override
    public Iterable<Product> getAllProductsByFilter(Sort sort) {
        return productRepository.findAll(sort);
    }
}
