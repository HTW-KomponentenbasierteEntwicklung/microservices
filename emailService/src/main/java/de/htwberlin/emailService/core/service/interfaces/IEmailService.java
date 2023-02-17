package de.htwberlin.emailService.core.service.interfaces;

import de.htwberlin.emailService.core.model.EmailAdress;
import de.htwberlin.emailService.core.model.Email;
import de.htwberlin.emailService.port.user.exception.EmailAdressNotFoundException;

import java.math.BigDecimal;
import java.util.UUID;

public interface IEmailService {

    Email generatePaymentConfirmEmail(EmailAdress account, BigDecimal amount, UUID orderNr);


    EmailAdress getEmailAdressByUsername(String username) throws EmailAdressNotFoundException;
    EmailAdress createEmailAdress(UUID userID, String username, String email);
}
