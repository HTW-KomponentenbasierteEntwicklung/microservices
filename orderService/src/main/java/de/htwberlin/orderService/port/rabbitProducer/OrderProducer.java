package de.htwberlin.orderService.port.rabbitProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Value("order_exchange")
    private String exchange;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(exchange, "", message);
    }
}
