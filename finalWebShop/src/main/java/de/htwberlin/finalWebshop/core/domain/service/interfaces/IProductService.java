package de.htwberlin.finalWebshop.core.domain.service.interfaces;

import de.htwberlin.finalWebshop.core.domain.model.Product;
import de.htwberlin.finalWebshop.port.product.user.exception.ProductNotFoundException;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * The Interface defines methods that can be applied to a Product.
 */
public interface IProductService {

    /**
     * Creates a new product and it's UUID.
     * @param product the new product with ALL parameters not null
     * @return the new product
     */
    Product createProduct(Product product);

    /**
     * Overwrites a product when the Id was found.
     * Does not create a new product.
     * @param id Product Id
     * @param product new Product Information
     * @return the updated Product
     * @throws ProductNotFoundException when the Product Id wasn't found
     */
    Product updateProduct(UUID id, Product product) throws ProductNotFoundException;

    /**
     * Deletes the Product with the given Id.
     * No information when the Product Id doesn't exist.
     * @param id Product Id
     */
    void deleteProduct(UUID id);

    /**
     * Finds a single product by it's Id.
     * @param id Id in UUID format
     * @return the product
     * @throws ProductNotFoundException when product not found (null)
     */
    Product getProductById(UUID id) throws ProductNotFoundException;

    /**
     *  Finds all products.
     *  Null when no products were found.
     * @return List with all products
     */
    Iterable<Product> getAllProducts();

    List<Product> findProductsByName(String name);

    Iterable<Product> getAllProductsByFilter(Sort sort);

}
