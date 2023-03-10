package de.htwberlin.paymentService.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.util.UUID;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;
    @NotNull
    @Getter @Setter
    private UUID orderNr;
    @Getter
    @Setter
    private String username;

    @NotNull
    @Getter
    @Setter
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Getter
    @Setter
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private PaymentMethod method;

    public Payment() {
    }

    public Payment(UUID orderNr, String username, BigDecimal amount, PaymentStatus status, PaymentMethod method) {
        this.orderNr = orderNr;
        this.username = username;
        this.amount = amount;
        this.status = status;
        this.method = method;
    }
}
