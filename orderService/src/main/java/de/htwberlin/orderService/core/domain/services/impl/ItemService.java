package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.OrderServiceApplication;
import de.htwberlin.orderService.core.domain.model.Item;
import de.htwberlin.orderService.core.domain.services.interfaces.IItemRepository;
import de.htwberlin.orderService.core.domain.services.interfaces.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public List<Item> addItemToOrder(List<Item> items) {
        List<Item> returnItems = new ArrayList<>();
        for(int i=0; i<items.size(); i++){
            Item savedItem = itemRepository.save(items.get(i));
            returnItems.add(savedItem);
        }

        return returnItems;
    }

    @Override
    public List<Item> getItemsForOrderId(UUID id) {
        return itemRepository.findByOrderID(id);
    }
}
