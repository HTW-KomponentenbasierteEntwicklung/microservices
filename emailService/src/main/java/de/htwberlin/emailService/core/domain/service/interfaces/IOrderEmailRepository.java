package de.htwberlin.emailService.core.domain.service.interfaces;

import de.htwberlin.emailService.core.domain.model.OrderEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IOrderEmailRepository extends JpaRepository<OrderEmail, UUID> {
    List<OrderEmail> findByOrderId(UUID orderId);

}
