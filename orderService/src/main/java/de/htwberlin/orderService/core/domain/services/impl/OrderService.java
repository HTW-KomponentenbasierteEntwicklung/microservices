package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.core.domain.model.*;
import de.htwberlin.orderService.core.domain.services.exception.OrderNotFoundServicesException;
import de.htwberlin.orderService.core.domain.services.interfaces.*;
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
        OrderRegistry orderNumber = orderRegistryRepository.save(new OrderRegistry(username, date));
        UUID orderId = orderNumber.getOrderId();
        OrderContact orderContact = orderContactService.createOrderContact(order.getOrderContact(), orderId);
        List<Item> items = itemService.addItemToOrder(orderId, order.getItems());
        TotalAmount totalAmount = totalAmountService.createTotalAmount(order.getTotalAmount(), orderId);
        Order returnOrder = new Order(orderContact,items,totalAmount,orderNumber);
        return returnOrder;

    }

    @Override
    public List<OrderRegistry> getOrderRegistryByUsername(String username) {
        return orderRegistryRepository.findByUsername(username);
    }

    @Override
    public Order getOrderbyid(UUID id) {
        OrderContact contact = orderContactService.getOrderContactByOrderId(id);
        List<Item> items = itemService.getItemsForOrderId(id);
        TotalAmount totalAmount = totalAmountService.getTotalAmountbyOrderId(id);
        OrderRegistry orderRegistry = orderRegistryRepository.getByOrderId(id);
        return new Order(contact,items,totalAmount, orderRegistry);

    }
}
