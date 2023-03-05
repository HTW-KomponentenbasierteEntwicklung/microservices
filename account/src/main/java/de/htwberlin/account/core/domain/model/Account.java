package de.htwberlin.account.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import java.sql.Date;
import java.util.UUID;
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String username;

    private String firstname;

    private String lastname;



    private Date birthday;
    private Gender gender;
    private String email;


    public Account() {
    }
    public Account(String username){
        this.username=username;
}
    public Account(String username, String firstname, String lastname, Date birthday, Gender gender, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
    }

}
