package de.htwberlin.cartService.core.domain.model;

import de.htwberlin.cartService.core.domain.model.Item;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
    private List<Item> items;
    private BigDecimal totalAmount;

    public Cart(List<Item> items, BigDecimal totalAmount) {
        this.items = items;
        this.totalAmount = totalAmount;
    }
}
