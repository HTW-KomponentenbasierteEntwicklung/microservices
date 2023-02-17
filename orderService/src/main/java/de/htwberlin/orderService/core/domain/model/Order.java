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
    private OrderContact orderContact;
    @Getter
    private List<Item> items;
    @Getter
    private TotalAmount totalAmount;
    @Getter
    private OrderRegistry orderRegistry;

    public Order(OrderContact orderContact, List<Item> items, TotalAmount totalAmount, OrderRegistry orderRegistry) {
        this.orderContact = orderContact;
        this.items = items;
        this.totalAmount = totalAmount;
        this.orderRegistry=orderRegistry;
    }

    public Order() {
    }


}
