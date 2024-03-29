package de.htwberlin.paymentService.port.product.user.advice;

import de.htwberlin.paymentService.port.product.user.exception.PaymentWithOrderIdAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentCannotBeCreatedAdvice {

    @ResponseBody
    @ExceptionHandler(value = PaymentWithOrderIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String productIdAlreadyExistsHandler(PaymentWithOrderIdAlreadyExistsException ex){
        return ex.getMessage();
    }
}
