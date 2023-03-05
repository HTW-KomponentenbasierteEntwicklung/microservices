package de.htwberlin.cartService.core.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    UUID id;
    @Getter
    String username;
    @Getter
    UUID productId;
    @Getter @Setter
    String productname;
    @Getter @Setter
    BigDecimal productPrice;
    @Getter @Setter
    String imageLink;
    @Getter @Setter
    int amount;

}
