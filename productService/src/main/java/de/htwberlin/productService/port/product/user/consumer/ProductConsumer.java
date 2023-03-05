package de.htwberlin.productService.port.product.user.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.productService.core.domain.model.Product;
import de.htwberlin.productService.core.domain.service.interfaces.IProductService;
import de.htwberlin.productService.port.product.dto.ProductChangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = {"cart-to-product"})
    public void consumeProductQuantityChange(String productchange){
        ObjectMapper mapper = new ObjectMapper();
        ProductChangeDTO productChangeDTO = null;
        try {
            productChangeDTO = mapper.readValue(productchange, ProductChangeDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Product product = productService.getProductById(productChangeDTO.getProductId());
        product.setAmount(product.getAmount()+productChangeDTO.getDifference());
        productService.updateProduct(productChangeDTO.getProductId(), product);
    }
}
