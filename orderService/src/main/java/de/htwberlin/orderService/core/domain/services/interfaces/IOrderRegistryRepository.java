package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.OrderRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IOrderRegistryRepository extends JpaRepository<OrderRegistry, UUID> {
    public List<OrderRegistry> findByUsername(String username);
    public OrderRegistry getByOrderId(UUID id);

}
