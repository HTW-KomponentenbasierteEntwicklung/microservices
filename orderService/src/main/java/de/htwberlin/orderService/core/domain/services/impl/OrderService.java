package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.core.domain.model.*;
import de.htwberlin.orderService.core.domain.services.exception.NotFoundByOrderIdException;
import de.htwberlin.orderService.core.domain.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private final ITotalAmountService totalAmountService;
    @Autowired
    private final IItemService itemService;

    public OrderService(IOrderContactRepository orderRepository, IItemRepository itemRepository, IOrderRegistryRepository orderNummerRepository, IOrderContactService orderContactService, ITotalAmountService totalAmountService, IItemService itemService) {
        this.orderRegistryRepository = orderNummerRepository;
        this.orderContactService = orderContactService;
        this.totalAmountService = totalAmountService;
        this.itemService = itemService;
    }


    @Override
    public Order createOrder(Order order, String username, Date date) {
        OrderRegistry orderRegistry = orderRegistryRepository.save(new OrderRegistry(username, date));
        UUID orderId = orderRegistry.getOrderId();
        OrderContact orderContact = order.getOrderContact();
        orderContact.setOrderId(orderId);

        List<Item> items = order.getItems();
        BigDecimal total = new BigDecimal(0);
        for(int i=0; i<items.size(); i++) {
            items.get(i).setOrderID(orderId);
            total.add(items.get(i).getTotal());
        }

        TotalAmount totalAmount = new TotalAmount(orderId, total);


        orderRegistryRepository.save(orderRegistry);
        orderContactService.createOrderContact(orderContact);
        totalAmountService.createTotalAmount(totalAmount);
        itemService.addItemToOrder(items);

        Order returnOrder = new Order(orderContact,items,totalAmount,orderRegistry);
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
        TotalAmount totalAmount = totalAmountService.getTotalAmountbyOrderId(orderId);
        OrderRegistry orderRegistry = orderRegistryRepository.getByOrderId(orderId);
        return new Order(contact,items,totalAmount, orderRegistry);

    }
}
