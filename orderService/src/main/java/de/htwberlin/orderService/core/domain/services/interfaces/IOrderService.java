package de.htwberlin.orderService.core.domain.services.interfaces;

import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.core.domain.model.OrderRegistry;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IOrderService {

    public Order createOrder(Order order, String username, Date date);
    public List<OrderRegistry> getOrderRegistryByUsername(String username);
    public Order getOrderbyid(UUID id);
}
