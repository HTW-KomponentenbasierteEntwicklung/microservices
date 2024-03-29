package de.htwberlin.cartService.port.dtomapper;

import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.port.dto.ProductChangeDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public class ItemToProductDTOMapper {
    public ProductChangeDTO getProductChangeDTO(Item item, int difference){
        ProductChangeDTO productDTO = new ProductChangeDTO();
        productDTO.setId(item.getProductId());
        productDTO.setChangeAmount(difference);
        return productDTO;
    }
}
