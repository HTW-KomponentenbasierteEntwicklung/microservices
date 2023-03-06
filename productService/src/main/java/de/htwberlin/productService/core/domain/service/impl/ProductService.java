package de.htwberlin.productService.core.domain.service.impl;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.exception.ProductIDNotFoundException;
import de.htwberlin.productService.core.domain.service.interfaces.IProductRepository;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import org.springframework.beans.BeanUtils;
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
    public Product updateProductAmount(UUID productId, int difference) throws ProductIDNotFoundException {
        Product existingProduct = productRepository.getById(productId);
        if (existingProduct == null){
            throw new ProductIDNotFoundException(productId);
        }
        existingProduct.setAmount(existingProduct.getAmount() + difference);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(UUID id){
        productRepository.deleteById(id) ;
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.getById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findByName(name);

    }
    @Override
    public List<Product> findProductsByCategory(Category category) {
        return productRepository.findByCategory( category);
    }
}
