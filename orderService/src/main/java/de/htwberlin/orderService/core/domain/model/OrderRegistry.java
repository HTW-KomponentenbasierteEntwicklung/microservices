package de.htwberlin.orderService.core.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class OrderRegistry {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Getter
    private UUID orderId;
    @Setter
    @Getter
    private String username;
    @Getter
    private Date date;
    @Getter
    private BigDecimal totalAmount;
    public OrderRegistry() {
    }

    public OrderRegistry(String username, Date date, BigDecimal totalAmount) {
        this.username = username;
        this.date = date;
        this.totalAmount = totalAmount;
    }

}
