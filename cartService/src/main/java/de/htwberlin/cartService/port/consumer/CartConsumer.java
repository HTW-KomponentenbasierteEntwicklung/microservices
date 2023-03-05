package de.htwberlin.cartService.port.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.cartService.core.domain.services.interfaces.ICartService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class CartConsumer {
    @Autowired
    private ICartService cartService;

    @RabbitListener(queues = {"product_cart"})
    public void consumeProductChange(String product){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = objectMapper.readTree(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        cartService.updateProductDataForItems(UUID.fromString(node.get("id").toString()),
                                                node.get("productName").toString(),node.get("imgLink").toString(), node.get("price").decimalValue());
    }

}
