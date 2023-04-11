package de.htwberlin.productService.port.product.user.advice;

import de.htwberlin.productService.port.product.user.exception.ProductIdNotFoundException;
import de.htwberlin.productService.port.product.user.exception.ProductNotFoundException;
import de.htwberlin.productService.port.product.user.exception.ProductsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(value = ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFoundHandler(ProductNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = ProductIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productIdNotFoundHandler(ProductIdNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String illegalArgumentHandler(IllegalArgumentException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = ProductsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productsNotFoundHandler(ProductsNotFoundException ex){
        return ex.getMessage();
    }

}
