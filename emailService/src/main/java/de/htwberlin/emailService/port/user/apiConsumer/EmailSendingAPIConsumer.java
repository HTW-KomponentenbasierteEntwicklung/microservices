package de.htwberlin.emailService.port.user.apiConsumer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import de.htwberlin.emailService.core.model.EmailAdress;
import de.htwberlin.emailService.core.model.Email;

import de.htwberlin.emailService.core.service.interfaces.IEmailService;
import de.htwberlin.emailService.port.dto.PaymentEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailSendingAPIConsumer {
    private final String API_KEY = "afd1b6ff706ff589e88a439f9231cbc1-d1a07e51-5806315a";
    private final String BASE_URL_EU = "https://api.mailgun.net/";
    private final String DOMAIN_NAME = "sandbox4049035d035e4b08b6f1c79c57a97079.mailgun.org";
    @Autowired
    private IEmailService emailService;
    public MessageResponse sendPaymentConfirmationEmail(EmailAdress account, PaymentEmailDTO payment) {
        Email email = emailService.generatePaymentConfirmEmail(account, payment.getAmount(), payment.getOrderNr());

        Message message = Message.builder()
                .from("WebshopSleep <"+DOMAIN_NAME+">")
                .to(email.getReceiver())
                .subject(email.getSubject())
                .text(email.getContent())
                .build();

        return mailgunMessagesApi().sendMessage(DOMAIN_NAME, message);
    }
    private MailgunMessagesApi mailgunMessagesApi() {
        return MailgunClient.config(BASE_URL_EU, API_KEY)
                .createApi(MailgunMessagesApi.class);
    }
    public MessageResponse sendmail(String payment) {

        Message message = Message.builder()
                .from("WebshopSleep <mailgun@"+DOMAIN_NAME+">")
                .to("mylinh.dao@t-online.de")
                .subject("TestEmail")
                .text("Just ignore this.")
                .build();

        return mailgunMessagesApi().sendMessage(DOMAIN_NAME, message);
    }

}
