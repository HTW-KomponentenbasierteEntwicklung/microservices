package de.htwberlin.productService.port.product.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.dto.ProductChangeDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductConsumer {
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    IProductService productService;

    @RabbitListener(queues = {"cartToProduct"})
    public void consume(String message){
        ProductChangeDTO productChangeDTO = null;
        try {
            productChangeDTO = mapper.readValue(message, ProductChangeDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        productService.updateProductAmount(productChangeDTO.getId(), productChangeDTO.getChangeAmount());

    }

}
