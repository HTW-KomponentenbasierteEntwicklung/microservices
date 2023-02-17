package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.TotalAmount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITotalAmountRepository extends JpaRepository<TotalAmount, UUID> {
    public TotalAmount getByOrderId(UUID id);
}
