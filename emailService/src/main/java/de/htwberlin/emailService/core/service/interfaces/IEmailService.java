package de.htwberlin.emailService.core.service.interfaces;

import de.htwberlin.emailService.core.model.OrderEmail;
import de.htwberlin.emailService.core.model.Email;

import java.math.BigDecimal;
import java.util.UUID;

public interface IEmailService {

    Email generatePaymentConfirmEmail(BigDecimal amount, UUID orderNr);

    Email generateOrderConfirmEmail(BigDecimal amount, OrderEmail orderEmail);
    OrderEmail createOrderEmail(String username, String email, UUID orderId);

    OrderEmail getOrderEmailByOrderId(UUID orderId);

}
