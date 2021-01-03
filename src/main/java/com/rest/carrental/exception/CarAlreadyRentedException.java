package com.rest.carrental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CarAlreadyRentedException extends RuntimeException {
    public CarAlreadyRentedException(Long id) {
        super(String.format("Car with id: %s is already rented.", id));
    }
}
