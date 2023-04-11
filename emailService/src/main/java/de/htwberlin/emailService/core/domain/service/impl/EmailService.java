package de.htwberlin.emailService.core.domain.service.impl;

import de.htwberlin.emailService.core.domain.model.OrderEmail;
import de.htwberlin.emailService.core.domain.model.Email;
import de.htwberlin.emailService.core.domain.service.exception.EmailAddressForOrderIdNotFoundException;
import de.htwberlin.emailService.core.domain.service.interfaces.IOrderEmailRepository;
import de.htwberlin.emailService.core.domain.service.interfaces.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private final IOrderEmailRepository orderEmailRepository;

    @Override
    public Email generatePaymentConfirmEmail(BigDecimal amount, UUID orderId) throws EmailAddressForOrderIdNotFoundException {
        List<OrderEmail> orderEmailList = orderEmailRepository.findByOrderId(orderId);
        if(orderEmailList.size() == 0){
            throw new EmailAddressForOrderIdNotFoundException();
        }
        OrderEmail orderEmail = orderEmailList.get(0);
        String content = "Hello "+orderEmail.getUsername()+", " +
                "\nwe have received your Payment on Order "+orderId+" with "+amount+" EUR." +
                "\n Thank you very much!";
        return new Email(orderEmail.getEmailAddress(), "We received your payment!", content);

    }

    @Override
    public Email generateOrderConfirmEmail(BigDecimal amount, OrderEmail orderEmail) {
        String content = "Hello "+orderEmail.getUsername()+", \n"+
                "we received your order "+orderEmail.getOrderId()+ ".\n"+
                "You need to pay "+amount+" EUR as fast as you can :)";
        return new Email(orderEmail.getEmailAddress(), "We received your order!", content);
    }

    @Override
    public OrderEmail createOrderEmail( String username, String email, UUID orderId) {
        return orderEmailRepository.save(new OrderEmail(orderId, username, email));
    }

    @Override
    public OrderEmail getOrderEmailByOrderId(UUID orderId) throws EmailAddressForOrderIdNotFoundException {
        List<OrderEmail> orderEmailList = orderEmailRepository.findByOrderId(orderId);
        if(orderEmailList.size() == 0){
            throw new EmailAddressForOrderIdNotFoundException();
        }else{
            return orderEmailList.get(0);
        }
    }
}
