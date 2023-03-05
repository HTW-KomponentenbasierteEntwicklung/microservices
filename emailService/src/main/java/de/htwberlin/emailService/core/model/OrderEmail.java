package de.htwberlin.emailService.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Entity
public class OrderEmail {
    public OrderEmail() {
    }


    public OrderEmail(UUID orderId, String emailAdress, String username) {
        this.orderId = orderId;
        this.emailAdress = emailAdress;
        this.username = username;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Getter
    @NotNull
    private UUID orderId;
    @Getter
    @NotNull
    private String emailAdress;
    @Getter
    @NotNull
    private String username;


}
