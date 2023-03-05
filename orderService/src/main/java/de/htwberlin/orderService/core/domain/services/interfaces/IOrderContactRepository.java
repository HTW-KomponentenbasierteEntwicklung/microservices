package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.OrderContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IOrderContactRepository extends JpaRepository<OrderContact, UUID> {
    List<OrderContact> findByOrderId(UUID orderId);
}
