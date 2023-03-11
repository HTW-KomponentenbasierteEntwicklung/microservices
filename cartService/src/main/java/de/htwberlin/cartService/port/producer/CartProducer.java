package de.htwberlin.cartService.port.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.cartService.CartServiceApplication;
import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.port.dto.ProductChangeDTO;
import de.htwberlin.cartService.port.dtomapper.ItemToProductDTOMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class CartProducer {
    private static final Logger log = LoggerFactory.getLogger(CartProducer.class);
    @Autowired
    private ItemToProductDTOMapper mapper;

    @Value("cart_exchange")
    private String exchange;

    @Value("")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public CartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void changeAmountOfProducts(Item item, int difference){
        ProductChangeDTO productChangeDTO = mapper.getProductChangeDTO(item, difference);
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(productChangeDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend(exchange, "cart.ToProduct", message);
    }
}
