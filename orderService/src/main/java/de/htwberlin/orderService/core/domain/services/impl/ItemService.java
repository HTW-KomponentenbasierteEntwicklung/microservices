package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.core.domain.model.Item;
import de.htwberlin.orderService.core.domain.services.interfaces.IItemRepository;
import de.htwberlin.orderService.core.domain.services.interfaces.IItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ItemService implements IItemService {
    private final IItemRepository itemRepository;

    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> addItemToOrder(UUID orderID, List<Item> items) {
        List<Item> returnItems = new ArrayList<>();
        for(int i=0; i<items.size(); i++){
            items.get(i).setOrderID(orderID);
            returnItems.add(itemRepository.save(items.get(i)));
        }
        return returnItems;
    }

    @Override
    public List<Item> getItemsForOrderId(UUID id) {
        return itemRepository.findByOrderID(id);
    }
}
