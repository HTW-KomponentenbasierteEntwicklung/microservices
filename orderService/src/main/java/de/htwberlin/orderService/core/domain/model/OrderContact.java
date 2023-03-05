package de.htwberlin.orderService.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class OrderContact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Getter @Setter
    private UUID orderId;
    @Getter
    private Gender gender;
    @Getter
    private String firstnamw;
    @Getter
    private String lastname;
    @Getter
    private String street;
    @Getter
    private String houseNr;
    @Getter
    private String postalCode;
    @Getter
    private String city;
    @Getter
    private String email;

}
