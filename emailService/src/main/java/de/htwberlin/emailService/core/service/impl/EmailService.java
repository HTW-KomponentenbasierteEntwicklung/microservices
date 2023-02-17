package de.htwberlin.emailService.core.service.impl;

import de.htwberlin.emailService.core.model.EmailAdress;
import de.htwberlin.emailService.core.model.Email;
import de.htwberlin.emailService.core.service.interfaces.IEmailAdressRepository;
import de.htwberlin.emailService.core.service.interfaces.IEmailService;
import de.htwberlin.emailService.port.user.exception.EmailAdressNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class EmailService implements IEmailService {

    public EmailService(IEmailAdressRepository emailAdressRepository) {
        this.emailAdressRepository = emailAdressRepository;
    }

    private IEmailAdressRepository emailAdressRepository;
    @Override
    public Email generatePaymentConfirmEmail(EmailAdress account, BigDecimal amount, UUID orderNr) {
        String content = "Hello "+account.getUsername()+", " +
                "\nwe have received your Payment on Order "+orderNr+" with "+amount+" EUR." +
                "\n Thank you very much!";
        return new Email(account.getEmailAdress(), "We received your payment!", content);


    }

    @Override
    public EmailAdress getEmailAdressByUsername(String username) throws EmailAdressNotFoundException{
        List<EmailAdress> emailAdress = emailAdressRepository.findByUsername(username);
        if (emailAdress.isEmpty()){
            throw new EmailAdressNotFoundException(username);
        }
        return emailAdress.get(0);
    }

    @Override
    public EmailAdress createEmailAdress(UUID userID, String username, String email) {
        return emailAdressRepository.save(new EmailAdress(userID, username, email));
    }


}
