package de.htwberlin.cartService.port.dtomapper;

import de.htwberlin.cartService.core.domain.model.Item;
import de.htwberlin.cartService.port.dto.ProductChangeDTO;
import org.springframework.stereotype.Service;

@Service

public class ItemToProductDTOMapper {
    public ProductChangeDTO getProductChangeDTO(Item item, int difference){
        System.out.println(item);
        ProductChangeDTO productDTO = new ProductChangeDTO();
        productDTO.setId(item.getProductId());
        productDTO.setChangeAmount(difference);
        return productDTO;
    }
}
