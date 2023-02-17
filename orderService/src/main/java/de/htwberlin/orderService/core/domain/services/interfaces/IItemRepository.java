package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.Item;
import de.htwberlin.orderService.core.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IItemRepository  extends JpaRepository<Item, UUID> {
    List<Item> findByOrderID(UUID orderID);
}
