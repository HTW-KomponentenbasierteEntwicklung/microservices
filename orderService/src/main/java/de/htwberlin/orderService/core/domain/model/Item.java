package de.htwberlin.orderService.core.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
public class Item implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Setter
    private UUID orderID;
    @Getter
    private UUID productID;
    @Getter
    private String productName;
    @Getter
    private int amount;
    @Getter
    private BigDecimal total;

    public Item() {
    }

    public Item(UUID id, UUID productID, String productName, int amount, BigDecimal total) {
        this.id = id;
        this.productID = productID;
        this.productName = productName;
        this.amount = amount;
        this.total = total;
    }

}
