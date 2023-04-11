package de.htwberlin.emailService.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class OrderEmail {

    public OrderEmail(UUID orderId, String emailAddress, String username) {
        this.orderId = orderId;
        this.emailAddress = emailAddress;
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
    private String emailAddress;
    @Getter
    @NotNull
    private String username;


}
