package de.htwberlin.productService.port.product.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.dto.ProductChangeDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductConsumer {

    private final IProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductConsumer.class);

    @RabbitListener(queues = {"cartToProduct"}) // Todo: Warum wo anders order.ToPayment? (Also mit Punkt?)
    public void consume(String message){    // Todo: Warum message und nicht ProductChangeDTO ? Dann kann ObjectMapper weggelassen werden
        ObjectMapper objectMapper = new ObjectMapper();
        ProductChangeDTO productChangeDTO = null;
        try {
            productChangeDTO = objectMapper.readValue(message, ProductChangeDTO.class);
            productService.updateProductAmount(productChangeDTO.getProductId(), productChangeDTO.getChangeAmount());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
