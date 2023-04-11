package de.htwberlin.orderService.port.dtoMapper;

import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.port.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderDTOMapper {
    public OrderDTO getMappedOrderDTO(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNr(order.getOrderRegistry().getOrderId());
        orderDTO.setEmail(order.getOrderContact().getEmail());
        orderDTO.setUsername(order.getOrderRegistry().getUsername());
        orderDTO.setTotalAmount(order.getTotalAmount());
        return orderDTO;
    }
}
