package de.htwberlin.cartService.port.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import de.htwberlin.cartService.port.dto.OrderDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class CartConsumer {
    private ObjectMapper mapper = new ObjectMapper();

    @Getter
    @Setter
    @Autowired
    ICartService cartService;

    @RabbitListener(queues = {"orderToCart"})
    public void consume(String message){
        OrderDTO orderDTO = null;
        try {
            orderDTO = mapper.readValue(message, OrderDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        cartService.removeAllItem(orderDTO.getUsername());
    }

}
