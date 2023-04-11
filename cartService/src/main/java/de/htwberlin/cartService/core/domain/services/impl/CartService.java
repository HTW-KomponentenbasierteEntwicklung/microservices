package de.htwberlin.cartService.core.domain.services.impl;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.core.domain.services.interfaces.ItemRepository;
import de.htwberlin.cartService.port.producer.CartProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CartService implements ICartService {
    private final ItemRepository itemRepository;
    private static final Logger log = LoggerFactory.getLogger(CartProducer.class);

    public CartService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    private List<Item> getItemsForUsername(String username) {
        return itemRepository.findByUsername(username);
    }


    @Override
    public Cart getCartForUsername(String username) {
        List<Item> items = getItemsForUsername(username);
        BigDecimal totalAmount = this.getTotalAmountForItemList(items);
        return new Cart(items, totalAmount);
    }

    @Override
    public Item getItemById(UUID id) {
        return itemRepository.getById(id);
    }

    @Override
    public Cart addItemToCart(Item item, String username) {
            item.setUsername(username);
            itemRepository.save(item);
        return getCartForUsername(username);
    }

    @Override
    public void removeAllItem(String username) {
        List<Item> itemList = itemRepository.findByUsername(username);
        for(int i=0; i<itemList.size(); i++){
            itemRepository.deleteById(itemList.get(i).getId());
        }
    }

    @Override
    public Cart deleteItemFromCart(UUID id) throws ItemNotFoundException {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException());

        itemRepository.deleteById(id);
        return getCartForUsername(item.getUsername());
    }


    private BigDecimal getTotalAmountForItemList(List<Item> itemList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for(int i=0; i<itemList.size(); i++){
            totalAmount = totalAmount.add(itemList.get(i).getProductPrice());
        }
        return totalAmount;
    }
}
