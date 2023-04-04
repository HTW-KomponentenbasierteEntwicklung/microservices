package de.htwberlin.productService.core.domain.service.impl;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductRepository;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.user.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public Product createProduct(Product product) throws ProductIdAlreadyExistsException {
        ProductValidator.validate(product);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(UUID productId) throws ProductIdNotFoundException{
        if (productId == null)
            throw new IllegalArgumentException("Product ID is invalid.");

        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductIdNotFoundException(productId));
    }

    @Override
    public void deleteProduct(UUID productId) throws ProductIdNotFoundException{
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductIdNotFoundException(productId));
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts() throws ProductsNotFoundException {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            throw new ProductsNotFoundException();
        return products;
    }

    @Override
    public List<Product> findProductsByName(String name) throws ProductsNotFoundException {
        List<Product> products = productRepository.findByName(name);
        if (products.isEmpty())
            throw new ProductsNotFoundException();
        return products;
    }

    @Override
    public List<Product> findProductsByCategory(Category category)  throws ProductsNotFoundException {
        List<Product> products =  productRepository.findByCategory(category);
        if (products.isEmpty())
            throw new ProductsNotFoundException();
        return products;
    }

    @Override
    public Product updateProductAmount(UUID productId, int difference) throws ProductIdNotFoundException {

        if (productId == null)
            throw new IllegalArgumentException("Product ID is missing or invalid");

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductIdNotFoundException(productId));
        existingProduct.setAmount(existingProduct.getAmount() + difference);
        return productRepository.save(existingProduct);
    }

}
