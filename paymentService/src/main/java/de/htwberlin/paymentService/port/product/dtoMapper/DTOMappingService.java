package de.htwberlin.paymentService.port.product.dtoMapper;

import de.htwberlin.paymentService.core.domain.model.Payment;
import de.htwberlin.paymentService.port.product.dto.PaymentEmailDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DTOMappingService{
    public PaymentEmailDTO convertPaymentToDTO(Payment payment) {
        PaymentEmailDTO dto = new PaymentEmailDTO();
        if (payment != null) {
            BeanUtils.copyProperties(payment, dto);
        }
        // https://javatodev.com/microservices-utility-payment-service-implementation/
        // Todo: ggf. abstract class vorlagern nach microservice architecture
        /*dto.setOrderNr(payment.getOrderNr());
        dto.setUsername(payment.getUsername());
        dto.setAmount(payment.getAmount());*/
        return dto;
    }
}
