package de.htwberlin.productService.core.domain.service.interfaces;

import de.htwberlin.productService.core.domain.model.Category;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.exception.ProductIDNotFoundException;

import java.util.List;
import java.util.UUID;
public interface IProductService {

    Product createProduct(Product product);

    Product updateProductAmount(UUID productId, int difference) throws ProductIDNotFoundException;

    void deleteProduct(UUID id) ;

    Product getProductById(UUID id);

    Iterable<Product> getAllProducts();

    List<Product> findProductsByName(String name);

    List<Product> findProductsByCategory(Category category);

}
