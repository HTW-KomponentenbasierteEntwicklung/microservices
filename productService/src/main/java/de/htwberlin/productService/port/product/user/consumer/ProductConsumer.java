package de.htwberlin.productService.port.product.user.consumer;

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

@Service //Todo: Oder @Component?
@RequiredArgsConstructor
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);
    private final IProductService productService;

    @RabbitListener(queues = {"cartToProduct"}) // Todo: Warum wo anders order.ToPayment? (Also mit Punkt?)
    public void consumeProductQuantityChange(String productchange){ // Todo: Warum String und nicht ProductChangeDTO ? Dann kann ObjectMapper weggelassen werde
        ObjectMapper mapper = new ObjectMapper();
        ProductChangeDTO productChangeDTO = null;
        try {
            productChangeDTO = mapper.readValue(productchange, ProductChangeDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        productService.updateProductAmount(productChangeDTO.getProductId(), productChangeDTO.getChangeAmount());
    }
}
