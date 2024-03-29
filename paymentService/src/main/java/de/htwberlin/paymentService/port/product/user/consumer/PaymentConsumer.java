package de.htwberlin.paymentService.port.product.user.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.core.domain.model.PaymentStatus;
import de.htwberlin.paymentService.core.domain.service.impl.PaymentService;
import de.htwberlin.paymentService.core.domain.service.interfaces.IPaymentService;
import de.htwberlin.paymentService.port.product.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);
    private final IPaymentService paymentService;

    @RabbitListener(queues = {"orderToPayment"})
    public void consumeOrder(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(message, OrderDTO.class);
            Payment payment = new Payment(orderDTO.getOrderId(), orderDTO.getUsername(), orderDTO.getTotalAmount(), PaymentStatus.PENDING, null);
            paymentService.createPayment(payment);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error processing message: {}", message, e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error creating payment for message: {}", message, e);
            throw new RuntimeException(e);
        }
    }
}
