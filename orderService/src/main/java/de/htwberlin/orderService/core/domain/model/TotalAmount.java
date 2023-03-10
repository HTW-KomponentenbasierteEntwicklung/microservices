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

    public TotalAmount() {
    }

    public TotalAmount(UUID orderId, BigDecimal itemsTotalAmount) {
        this.orderId = orderId;
        this.itemsTotalAmount = itemsTotalAmount;
        this.totalAmount = itemsTotalAmount.add(this.shipping);
    }

    @Getter @Setter
    private BigDecimal itemsTotalAmount;
    @Getter @Setter
    private BigDecimal shipping = new BigDecimal(0);
    @Getter @Setter
    private BigDecimal totalAmount;

}
