package de.htwberlin.emailService.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
public class EmailAdress {
    public EmailAdress() {
    }

    public EmailAdress(UUID userID, String emailAdress, String username) {
        this.userID = userID;
        this.emailAdress = emailAdress;
        this.username = username;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID emailAdressID;
    private UUID userID;
    @Getter
    @Setter
    @NotNull
    private String emailAdress;
    @Getter
    @Setter
    @NotNull
    private String username;


}
