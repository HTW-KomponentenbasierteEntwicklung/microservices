package de.htwberlin.cartService.core.domain.services.impl;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.core.domain.services.interfaces.ItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class CartService implements ICartService {
    private final ItemRepository itemRepository;

    public CartService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    private List<Item> getItemsForUsername(String username) {
        return itemRepository.findByUsername(username);
    }
    @Override
    public Cart changeAmountOfItemInCart(Item toUpdateItem) throws ItemNotFoundException {
        Item currentItem = itemRepository.getById(toUpdateItem.getId());
        if(currentItem == null){
            throw new ItemNotFoundException();
        }
        if(toUpdateItem.getAmount() <= 0){
            itemRepository.deleteById(toUpdateItem.getId());
        }else{
            itemRepository.save(toUpdateItem);
        }
        return this.getCartForUsername(toUpdateItem.getUsername());
    }

    @Override
    public Cart getCartForUsername(String username) {
        List<Item> items = getItemsForUsername(username);
        BigDecimal totalAmount = getTotalAmountForItemList(items);
        return new Cart(items, totalAmount);
    }

    @Override
    public Cart addItemToCart(Item item) {
        itemRepository.save( item);
        return getCartForUsername(item.getUsername());
    }

    @Override
    public void removeAllItem(String username) {
        List<Item> itemList = itemRepository.findByUsername(username);
        for(int i=0; i<itemList.size(); i++){
            itemRepository.deleteById(itemList.get(i).getId());
        }
    }

    @Override
    public int getAmountDifferenceOfItem(Item toUpdateItem) throws ItemNotFoundException {
        Item currentItem = itemRepository.getById(toUpdateItem.getId());
        if(currentItem == null){
            throw new ItemNotFoundException();
        }
        return toUpdateItem.getAmount()- currentItem.getAmount();
    }


    private BigDecimal getTotalAmountForItemList(List<Item> itemList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for(int i=0; i<itemList.size(); i++){
            totalAmount.add(itemList.get(i).getProductPrice());
        }
        return totalAmount;
    }
}
