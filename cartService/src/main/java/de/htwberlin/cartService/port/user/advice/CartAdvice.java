package de.htwberlin.cartService.port.user.advice;

import de.htwberlin.cartService.port.user.exception.NoSuchItemExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CartAdvice {

    @ResponseBody
    @ExceptionHandler(value = NoSuchItemExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String itemNotFoundAdvice(NoSuchItemExistsException ex){
        return ex.getMessage();
    }

}