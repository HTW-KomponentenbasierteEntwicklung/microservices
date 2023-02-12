package de.htwberlin.finalWebshop.core.domain.service.interfaces;

import de.htwberlin.finalWebshop.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findProductsByName(String name);
}
