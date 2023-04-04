package de.htwberlin.productService.core.domain.service.interfaces;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.port.product.user.exception.*;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    Product createProduct(Product product) throws ProductIdAlreadyExistsException;

    Product getProductById(UUID id) throws ProductIdNotFoundException;

    void deleteProduct(UUID id) throws ProductIdNotFoundException;

    List<Product> getAllProducts() throws ProductsNotFoundException;

    List<Product> findProductsByName(String name)  throws ProductsNotFoundException;

    List<Product> findProductsByCategory(Category category)  throws ProductsNotFoundException;
    Product updateProductAmount(UUID productId, int difference) throws ProductIdNotFoundException;

}
