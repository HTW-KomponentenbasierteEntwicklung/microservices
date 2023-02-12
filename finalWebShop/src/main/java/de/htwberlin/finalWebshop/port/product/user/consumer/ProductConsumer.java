package de.htwberlin.finalWebshop.port.product.user.consumer;

import de.htwberlin.finalWebshop.core.domain.service.interfaces.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = {"product"})
    public void consume(String message){

        LOGGER.info(String.format("Received message -> %s", message));
        productService.getProductById(UUID.randomUUID()); // Todo: UUID statt 123
    }
}
