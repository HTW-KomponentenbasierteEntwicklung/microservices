package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.OrderServiceApplication;
import de.htwberlin.orderService.core.domain.model.*;
import de.htwberlin.orderService.core.domain.services.exception.NotFoundByOrderIdException;
import de.htwberlin.orderService.core.domain.services.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class OrderService implements IOrderService {

    @Autowired
    private final IOrderRegistryRepository orderRegistryRepository;
    @Autowired
    private final IOrderContactService orderContactService;
    @Autowired
    private final IItemService itemService;

    public OrderService(IOrderContactRepository orderRepository, IItemRepository itemRepository, IOrderRegistryRepository orderNummerRepository, IOrderContactService orderContactService, IItemService itemService) {
        this.orderRegistryRepository = orderNummerRepository;
        this.orderContactService = orderContactService;
        this.itemService = itemService;
    }


    @Override
    public Order createOrder(Order order, String username, Date date) {
        OrderRegistry orderRegistry = orderRegistryRepository.save(new OrderRegistry(username, date, order.getTotalAmount()));
        UUID orderId = orderRegistry.getOrderId();
        OrderContact orderContact = order.getOrderContact();
        orderContact.setOrderId(orderId);

        List<Item> items = order.getItems();
        for(int i=0; i<items.size(); i++){
            items.get(i).setOrderID(orderId);
        }

        orderRegistryRepository.save(orderRegistry);
        orderContactService.createOrderContact(orderContact);
        itemService.addItemToOrder(items);

        Order returnOrder = new Order(orderContact,items,orderRegistry, order.getTotalAmount());
        return returnOrder;

    }

    @Override
    public List<OrderRegistry> getOrderRegistryByUsername(String username) {
        return orderRegistryRepository.findByUsername(username);
    }

    @Override
    public Order getOrderByOrderId(UUID orderId) throws NotFoundByOrderIdException {
        OrderContact contact = orderContactService.getOrderContactByOrderId(orderId);
        List<Item> items = itemService.getItemsForOrderId(orderId);
        OrderRegistry orderRegistry = orderRegistryRepository.getByOrderId(orderId);
        return new Order(contact,items, orderRegistry, orderRegistry.getTotalAmount());

    }
}
