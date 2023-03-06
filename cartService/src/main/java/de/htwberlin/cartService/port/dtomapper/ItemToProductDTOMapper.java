package de.htwberlin.cartService.port.dtomapper;

import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.port.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service

public class ItemToProductDTOMapper {
    public ProductDTO getProductDTOFromItem(Item item){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(item.getProductId());
        productDTO.setAmount(item.getAmount());
        return productDTO;
    }
}
