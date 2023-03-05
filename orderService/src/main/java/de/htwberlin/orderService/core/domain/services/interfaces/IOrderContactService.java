package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.OrderContact;
import de.htwberlin.orderService.core.domain.services.exception.NotFoundByOrderIdException;

import java.util.List;
import java.util.UUID;

public interface IOrderContactService {
    public OrderContact getOrderContactByOrderId(UUID id) throws NotFoundByOrderIdException;
    public OrderContact createOrderContact(OrderContact orderContact);
}
