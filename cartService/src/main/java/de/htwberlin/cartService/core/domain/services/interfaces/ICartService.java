package de.htwberlin.cartService.core.domain.services.interfaces;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;

import java.util.UUID;

public interface ICartService {
    public Cart getCartForUsername(String username);
    public Item getItemById(UUID id);
    public Cart addItemToCart(Item item, String username);
    public void removeAllItem(String username);

    Cart deleteItemFromCart(UUID id) throws ItemNotFoundException;
}
