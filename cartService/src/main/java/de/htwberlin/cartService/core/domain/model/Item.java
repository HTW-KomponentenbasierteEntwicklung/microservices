package de.htwberlin.cartService.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    UUID id;
    @Getter @Setter
    String username;
    @Getter
    UUID productId;
    @Getter @Setter
    String productname;
    @Getter @Setter
    BigDecimal productPrice;
    @Getter @Setter
    String imageLink;


}
