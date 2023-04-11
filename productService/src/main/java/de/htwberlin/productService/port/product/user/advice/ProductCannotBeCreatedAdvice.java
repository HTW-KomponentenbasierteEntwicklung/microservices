package de.htwberlin.productService.port.product.user.advice;

import de.htwberlin.productService.port.product.user.exception.ProductIdAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductCannotBeCreatedAdvice {

    @ResponseBody
    @ExceptionHandler(value = ProductIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String productIdAlreadyExistsHandler(ProductIdAlreadyExistsException ex){
        return ex.getMessage();
    }
}
