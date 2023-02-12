package de.htwberlin.paymentService.port.product.user.consumer;

import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    @Autowired
    private IPaymentService paymentService;

    @RabbitListener(queues = {"product"})
    public void consume(String message){

        LOGGER.info(String.format("Received message -> %s", message));
        paymentService.getPaymentById(UUID.randomUUID()); // Todo: UUID statt 123
    }
}
