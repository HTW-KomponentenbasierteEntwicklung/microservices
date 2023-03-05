package de.htwberlin.orderService.port.rabbitProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.orderService.core.domain.model.Order;
import de.htwberlin.orderService.port.dto.OrderDTO;
import de.htwberlin.orderService.port.dtoMapper.OrderDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Value("order_exchange")
    private String exchange;
    @Autowired
    private OrderDTOMapper orderDTOMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToAll(Order order){
        OrderDTO orderDTO= orderDTOMapper.getMappedOrderDTO(order);
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(orderDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend(exchange, "order.*", message);
    }
}
