package de.htwberlin.paymentService.port.product.user.producer;

import de.htwberlin.paymentService.core.domain.model.dto.PaymentEmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
public class PaymentProducer {

    @Value("payment_exchange")
    private String exchange;

    @Value("")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String payment){
        rabbitTemplate.convertAndSend(exchange, "email_payment_confirmation", payment);
    }
}