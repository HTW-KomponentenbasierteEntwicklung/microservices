package de.htwberlin.emailService.port.user.apiConsumer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import de.htwberlin.emailService.core.model.EmailAdress;
import de.htwberlin.emailService.core.model.Email;

import de.htwberlin.emailService.core.service.interfaces.IEmailService;
import de.htwberlin.emailService.port.dto.OrderDTO;
import de.htwberlin.emailService.port.dto.PaymentEmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailSendingAPIConsumer {
    private final String API_KEY = "your-api-key";
    private final String BASE_URL_EU = "https://api.mailgun.net/";
    private final String DOMAIN_NAME = "email.mailgun.org";
    private String sender = "WebshopFluffy <"+DOMAIN_NAME+">";
    @Autowired
    private IEmailService emailService;
    public MessageResponse sendPaymentConfirmationEmail(EmailAdress account, PaymentEmailDTO payment) {
        Email email = emailService.generatePaymentConfirmEmail(account, payment.getAmount(), payment.getOrderNr());

        Message message = messageBuilder(email);

        return mailgunMessagesApi().sendMessage(DOMAIN_NAME, message);
    }
    public MessageResponse sendOrderConfirmationEmail(EmailAdress account, OrderDTO order) {
        Email email = emailService.generateOrderConfirmEmail(account, order.getTotalAmount(), order.getOrderNr());

        Message message = messageBuilder(email);

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
    private Message messageBuilder(Email email){
        Message message = Message.builder()
                .from(sender)
                .to(email.getReceiver())
                .subject(email.getSubject())
                .text(email.getContent())
                .build();
        return message;
    }

}
