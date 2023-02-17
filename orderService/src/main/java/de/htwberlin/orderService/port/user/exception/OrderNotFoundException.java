package de.htwberlin.orderService.port.user.exception;

import java.util.UUID;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(UUID id) {
    }
}
