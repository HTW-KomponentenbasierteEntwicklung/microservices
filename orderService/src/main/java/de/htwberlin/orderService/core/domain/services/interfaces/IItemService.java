package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.Item;

import java.util.List;
import java.util.UUID;

public interface IItemService {
    public List<Item> addItemToOrder(List<Item> items);

    public List<Item> getItemsForOrderId(UUID id);

}
