package de.htwberlin.emailService.core.service.impl;

import de.htwberlin.emailService.core.model.OrderEmail;
import de.htwberlin.emailService.core.model.Email;
import de.htwberlin.emailService.core.service.exception.EmailAdressForOrderIdNotFoundException;
import de.htwberlin.emailService.core.service.interfaces.IOrderEmailRepository;
import de.htwberlin.emailService.core.service.interfaces.IEmailService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Service
public class EmailService implements IEmailService {

    public EmailService(IOrderEmailRepository orderEmailRepository) {
        this.orderEmailRepository = orderEmailRepository;
    }

    private IOrderEmailRepository orderEmailRepository;
    @Override
    public Email generatePaymentConfirmEmail(BigDecimal amount, UUID orderId) throws EmailAdressForOrderIdNotFoundException{
        List<OrderEmail> orderEmailList = orderEmailRepository.findByOrderId(orderId);
        if(orderEmailList.size() == 0){
            throw new EmailAdressForOrderIdNotFoundException();
        }
        OrderEmail orderEmail = orderEmailList.get(0);
        String content = "Hello "+orderEmail.getUsername()+", " +
                "\nwe have received your Payment on Order "+orderId+" with "+amount+" EUR." +
                "\n Thank you very much!";
        return new Email(orderEmail.getEmailAdress(), "We received your payment!", content);

    }

    @Override
    public Email generateOrderConfirmEmail(BigDecimal amount, OrderEmail orderEmail) {
        String content = "Hello "+orderEmail.getUsername()+", \n"+
                "we received your order "+orderEmail.getOrderId()+ ".\n"+
                "You need to pay "+amount+" EUR as fast as you can :)";
        return new Email(orderEmail.getEmailAdress(), "We received your order!", content);
    }

    @Override
    public OrderEmail createOrderEmail( String username, String email, UUID orderId) {
        return orderEmailRepository.save(new OrderEmail(orderId, username, email));
    }

    @Override
    public OrderEmail getOrderEmailByOrderId(UUID orderId) throws EmailAdressForOrderIdNotFoundException{
        List<OrderEmail> orderEmailList = orderEmailRepository.findByOrderId(orderId);
        if(orderEmailList.size() == 0){
            throw new EmailAdressForOrderIdNotFoundException();
        }else{
            return orderEmailList.get(0);
        }
    }


}
