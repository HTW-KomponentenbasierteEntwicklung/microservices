package de.htwberlin.orderService.core.domain.services.impl;

import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.core.domain.model.OrderContact;
import de.htwberlin.orderService.core.domain.services.exception.NotFoundByOrderIdException;
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
    public OrderContact getOrderContactByOrderId(UUID orderId) throws NotFoundByOrderIdException{
        List<OrderContact> orderContactList = orderContactRepository.findByOrderId(orderId);
        if(orderContactList.size() == 0){
            throw new NotFoundByOrderIdException();
        }
        return orderContactList.get(0);
    }

    @Override
    public OrderContact createOrderContact(OrderContact orderContact) {
        return orderContactRepository.save(orderContact);
    }
}
