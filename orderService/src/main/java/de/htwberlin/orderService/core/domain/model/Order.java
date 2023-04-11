package de.htwberlin.orderService.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
public class Order {
    @Getter
    UUID orderId;
    @Getter
    private OrderContact orderContact;
    @Getter
    private List<Item> items;
    @Getter
    private OrderRegistry orderRegistry;
    @Getter
    private BigDecimal totalAmount;

    public Order(OrderContact orderContact, List<Item> items, OrderRegistry orderRegistry, BigDecimal totalAmount) {
        this.orderId = orderContact.getOrderId();
        this.orderContact = orderContact;
        this.items = items;
        this.orderRegistry=orderRegistry;
    }

    public Order() {
    }


}
