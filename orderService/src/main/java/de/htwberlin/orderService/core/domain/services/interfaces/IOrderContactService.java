package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.OrderContact;

import java.util.List;
import java.util.UUID;

public interface IOrderContactService {
    public OrderContact getOrderContactByOrderId(UUID id);
    public List<OrderContact> getOrdersByUsername(String username);
    public OrderContact createOrderContact(OrderContact orderContact, UUID orderId);
}
