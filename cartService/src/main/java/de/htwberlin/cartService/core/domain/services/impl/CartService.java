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
    public Cart changeAmountOfItemInCart(Item toUpdateItem, String username) throws ItemNotFoundException {
        Optional<Item> currentItemOptional = itemRepository.findById(toUpdateItem.getId());
        if(!currentItemOptional.isPresent()){
            throw new ItemNotFoundException();
        }
        if(toUpdateItem.getAmount() <= 0){
            itemRepository.deleteById(toUpdateItem.getId());
        }else{
            toUpdateItem.setUsername(username);
            itemRepository.save(toUpdateItem);
        }
        return getCartForUsername(username);
    }

    @Override
    public Cart getCartForUsername(String username) {
        List<Item> items = getItemsForUsername(username);
        BigDecimal totalAmount = this.getTotalAmountForItemList(items);
        return new Cart(items, totalAmount);
    }

    @Override
    public Cart addItemToCart(Item item, String username) {
        List<Item> itemsInCartOfUsername = itemRepository.findByUsername(username);
        Item existingItemWithProductId = null;
        for(int i=0; i<itemsInCartOfUsername.size(); i++){
            if(itemsInCartOfUsername.get(i).getProductId().equals(item.getProductId())){
                existingItemWithProductId = itemsInCartOfUsername.get(i);
                break;
            }
        }
        if(existingItemWithProductId == null){
            item.setUsername(username);
            itemRepository.save( item);
        }else {
            existingItemWithProductId.setAmount(existingItemWithProductId.getAmount() + item.getAmount());
            itemRepository.save(existingItemWithProductId);
        }
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
    public int getAmountDifferenceOfItem(Item toUpdateItem) throws ItemNotFoundException {
        Optional<Item> currentItemOptional = itemRepository.findById(toUpdateItem.getId());
        if(!currentItemOptional.isPresent()){
            throw new ItemNotFoundException();
        }
        Item currentItem = currentItemOptional.get();
        return toUpdateItem.getAmount() - currentItem.getAmount();
    }


    private BigDecimal getTotalAmountForItemList(List<Item> itemList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for(int i=0; i<itemList.size(); i++){
            BigDecimal amount = new BigDecimal(itemList.get(i).getAmount());
            totalAmount = totalAmount.add(itemList.get(i).getProductPrice().multiply(amount));
        }
        return totalAmount;
    }
}
