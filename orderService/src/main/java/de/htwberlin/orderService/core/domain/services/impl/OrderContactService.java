package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.core.domain.model.OrderContact;
import de.htwberlin.orderService.core.domain.services.interfaces.IOrderContactRepository;
import de.htwberlin.orderService.core.domain.services.interfaces.IOrderContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class OrderContactService implements IOrderContactService{
    private final IOrderContactRepository orderContactRepository;

    public OrderContactService(IOrderContactRepository orderContactRepository) {
        this.orderContactRepository = orderContactRepository;
    }

    @Override
    public OrderContact getOrderContactByOrderId(UUID id) {
        return null;
    }

    @Override
    public List<OrderContact> getOrdersByUsername(String username) {
        return null;
    }

    @Override
    public OrderContact createOrderContact(OrderContact orderContact, UUID orderId) {
        orderContact.setOrderId(orderId);
        return orderContactRepository.save(orderContact);
    }
}
