package de.htwberlin.emailService.port.user.RabbitMQConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.emailService.EmailServiceApplication;
import de.htwberlin.emailService.core.model.EmailAdress;
import de.htwberlin.emailService.core.service.interfaces.IEmailService;
import de.htwberlin.emailService.port.dto.PaymentEmailDTO;
import de.htwberlin.emailService.port.user.apiConsumer.EmailSendingAPIConsumer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class EmailSendingRabbitMQConsumer {
    private static final Logger log = LoggerFactory.getLogger(EmailServiceApplication.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    EmailSendingAPIConsumer emailAPIConsumer;
    @Autowired
    IEmailService emailService;
    @RabbitListener(queues = {"emailPaymentConfirmation"})
    public void consumePaymentReceived(String payment){
        log.info(payment);
        PaymentEmailDTO paymentEmailDTO = null;
        try {
            paymentEmailDTO = mapper.readValue(payment, PaymentEmailDTO.class);
        } catch (JsonProcessingException e) {
            log.error("Message could not be decoded.");
            return;
        }
        EmailAdress emailAdress = emailService.getEmailAdressByUsername(paymentEmailDTO.getUsername());
        emailAPIConsumer.sendPaymentConfirmationEmail(emailAdress, paymentEmailDTO);
    }
}
