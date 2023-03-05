package de.htwberlin.cartService.core.domain.services.interfaces;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;

import java.math.BigDecimal;
import java.util.UUID;

public interface ICartService {
    public void  updateProductDataForItems(UUID productId, String productname, String imagelink, BigDecimal price);
    public Cart changeAmount(Item newItem);
    public Cart getCartForUsername(String username);
    public Cart addItemToCart(Item item);
}
