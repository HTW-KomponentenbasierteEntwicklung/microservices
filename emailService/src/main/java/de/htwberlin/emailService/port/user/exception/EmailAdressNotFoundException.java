package de.htwberlin.emailService.port.user.exception;

import java.util.UUID;

public class EmailAdressNotFoundException extends RuntimeException {
    public EmailAdressNotFoundException(UUID id){super("Email with id "+id+" cannot be found.");}
    public EmailAdressNotFoundException(String username){super("Email with username "+username+" cannot be found.");}

}
