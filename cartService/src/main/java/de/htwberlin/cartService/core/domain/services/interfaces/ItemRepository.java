package de.htwberlin.cartService.core.domain.services.interfaces;

import de.htwberlin.cartService.core.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByUsername(String username);
    List<Item> findByProductId(UUID productId);
}
