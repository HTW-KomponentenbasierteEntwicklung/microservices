package de.htwberlin.cartService.core.domain.services.interfaces;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;

import java.util.List;

public interface ICartService {
    public Cart changeAmountOfItemInCart(Item newItem, String username) throws ItemNotFoundException;
    public Cart getCartForUsername(String username);
    public Cart addItemToCart(Item item, String username);
    public void removeAllItem(String username);
    int getAmountDifferenceOfItem(Item toUpdateItem) throws ItemNotFoundException;
}
