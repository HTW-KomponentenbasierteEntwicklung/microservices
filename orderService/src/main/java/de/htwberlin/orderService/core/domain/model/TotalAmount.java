package de.htwberlin.orderService.core.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class TotalAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Setter
    private UUID orderId;
    @Getter @Setter
    private BigDecimal itemsTotalAmount;
    @Getter @Setter
    private BigDecimal shipping = new BigDecimal(5);
    @Getter @Setter
    private BigDecimal totalAmount;

}
