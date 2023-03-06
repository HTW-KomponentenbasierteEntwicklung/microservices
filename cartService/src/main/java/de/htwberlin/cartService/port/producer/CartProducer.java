package de.htwberlin.cartService.port.producer;

import de.htwberlin.cartService.port.dto.ProductDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartProducer {

    @Value("cart_exchange")
    private String exchange;

    @Value("")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public CartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void changeAmountOfProducts(ProductDTO productDTO){
        rabbitTemplate.convertAndSend(exchange, "cart.ToProduct", productDTO);
    }
}
