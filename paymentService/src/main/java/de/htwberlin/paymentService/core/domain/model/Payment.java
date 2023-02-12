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
    private UUID order;

    private String payer;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PaymentMethod method;

    public Payment() {
    }

    public Payment(UUID order, String payer, BigDecimal amount, PaymentStatus status, PaymentMethod method) {
        this.order = order;
        this.payer = payer;
        this.amount = amount;
        this.status = status;
        this.method = method;
    }
}
