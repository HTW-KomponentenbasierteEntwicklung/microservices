package de.htwberlin.cartService.port.user.exception;

import de.htwberlin.cartService.core.domain.services.exception.ItemNotFoundException;

public class NoSuchItemExistsException extends Throwable {
    public NoSuchItemExistsException(ItemNotFoundException e) {
    }
}
