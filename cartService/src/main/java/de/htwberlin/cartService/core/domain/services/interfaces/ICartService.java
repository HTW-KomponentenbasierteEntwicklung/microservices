package de.htwberlin.cartService.core.domain.services.interfaces;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;

public interface ICartService {
    public Cart changeAmountOfItem(Item newItem);
    public Cart getCartForUsername(String username);
    public Cart addItemToCart(Item item);
    public void removeAllItem(String username);
}
