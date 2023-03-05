package de.htwberlin.cartService.core.domain.services.impl;

import de.htwberlin.cartService.core.domain.model.Cart;
import de.htwberlin.cartService.core.domain.model.Item;
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



    @Override
    public void updateProductDataForItems(UUID productId, String productname, String imagelink, BigDecimal price) {
        List<Item> itemList = itemRepository.findByProductId(productId);
        for(int i=0; i<itemList.size(); i++){
            Item item = itemList.get(i);
            item.setProductname(productname);
            item.setProductPrice(price);
            item.setImageLink(imagelink);
            itemRepository.save(item);
        }
    }

    private List<Item> getItemsForUsername(String username) {
        return itemRepository.findByUsername(username);
    }
    @Override
    public Cart changeAmount(Item newItem) {
        Item currentItem = itemRepository.getById(newItem.getId());
        if(newItem.getAmount() <= 0){
            itemRepository.deleteById(newItem.getId());
        }else{
            itemRepository.save(newItem);
        }
        return this.getCartForUsername(newItem.getUsername());
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

    private BigDecimal getTotalAmountForItemList(List<Item> itemList) {
        BigDecimal totalAmount = new BigDecimal(0);
        for(int i=0; i<itemList.size(); i++){
            totalAmount.add(itemList.get(i).getProductPrice());
        }
        return totalAmount;
    }
}
