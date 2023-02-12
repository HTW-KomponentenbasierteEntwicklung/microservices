package de.htwberlin.paymentService.port.product.user.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    @Value("payment_exchange")
    private String exchange;

    @Value("")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public ProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}