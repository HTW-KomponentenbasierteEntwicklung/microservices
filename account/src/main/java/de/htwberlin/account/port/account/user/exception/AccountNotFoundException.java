package de.htwberlin.account.port.account.user.exception;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(UUID id) {
        super("Account not found with id " + id);
    }

    public AccountNotFoundException(String username) {
        super("Account not found with username " + username);
    }

}
