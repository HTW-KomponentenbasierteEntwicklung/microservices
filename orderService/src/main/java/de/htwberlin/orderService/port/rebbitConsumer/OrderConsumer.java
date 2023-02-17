package de.htwberlin.orderService.port.rebbitConsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @RabbitListener(queues = {"self"})
    public void consume(String message) {
    }

}
