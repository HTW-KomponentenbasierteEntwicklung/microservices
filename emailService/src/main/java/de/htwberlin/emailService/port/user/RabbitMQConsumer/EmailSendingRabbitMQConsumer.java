package de.htwberlin.emailService.port.user.RabbitMQConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.emailService.core.model.OrderEmail;
import de.htwberlin.emailService.core.service.interfaces.IEmailService;
import de.htwberlin.emailService.port.dto.OrderDTO;
import de.htwberlin.emailService.port.dto.PaymentEmailDTO;
import de.htwberlin.emailService.port.user.apiConsumer.EmailSendingAPIConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSendingRabbitMQConsumer {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    EmailSendingAPIConsumer emailAPIConsumer;
    @Autowired
    IEmailService emailService;
    @RabbitListener(queues = {"paymentToEmail"})
    public void consumePaymentSuccess(String payment){
        PaymentEmailDTO paymentEmailDTO = null;
        try {
            paymentEmailDTO = mapper.readValue(payment, PaymentEmailDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        emailAPIConsumer.sendPaymentConfirmationEmail(paymentEmailDTO);
    }
    @RabbitListener(queues = {"orderToEmail"})
    public void consumeOrderReceived(String order){
        OrderDTO orderDTO = null;
        try {
            orderDTO = mapper.readValue(order, OrderDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        OrderEmail emailAdress = emailService.createOrderEmail(orderDTO.getUsername(),orderDTO.getEmail(), orderDTO.getOrderId());
        emailAPIConsumer.sendOrderConfirmationEmail(orderDTO);

    }
}
