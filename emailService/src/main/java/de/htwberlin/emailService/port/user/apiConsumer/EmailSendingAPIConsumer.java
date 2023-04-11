package de.htwberlin.emailService.port.user.apiConsumer;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import de.htwberlin.emailService.core.domain.model.OrderEmail;
import de.htwberlin.emailService.core.domain.model.Email;

import de.htwberlin.emailService.core.domain.service.exception.EmailAddressForOrderIdNotFoundException;
import de.htwberlin.emailService.core.domain.service.interfaces.IEmailService;
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
    public MessageResponse sendPaymentConfirmationEmail(PaymentEmailDTO payment) {
        Email email = emailService.generatePaymentConfirmEmail(payment.getAmount(), payment.getOrderNr());

        Message message = messageBuilder(email);

        return mailgunMessagesApi().sendMessage(DOMAIN_NAME, message);
    }
    public MessageResponse sendOrderConfirmationEmail(OrderDTO order) {
        OrderEmail orderEmail;
        try{
            orderEmail = emailService.getOrderEmailByOrderId(order.getOrderId());
        }catch (EmailAddressForOrderIdNotFoundException e){
            throw new RuntimeException(e);
        }
        Email email = emailService.generateOrderConfirmEmail(order.getTotalAmount(), orderEmail);

        Message message = messageBuilder(email);

        return mailgunMessagesApi().sendMessage(DOMAIN_NAME, message);
    }
    private MailgunMessagesApi mailgunMessagesApi() {
        return MailgunClient.config(BASE_URL_EU, API_KEY)
                .createApi(MailgunMessagesApi.class);
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
