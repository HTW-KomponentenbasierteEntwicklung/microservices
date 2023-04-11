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
    private UUID productId;
    @Getter
    private String productname;

    @Getter
    private BigDecimal price;

    public Item() {
    }

    public Item(UUID id, UUID productID, String productName, BigDecimal price) {
        this.id = id;
        this.productId = productID;
        this.productname = productName;
        this.price = price;
    }

}
