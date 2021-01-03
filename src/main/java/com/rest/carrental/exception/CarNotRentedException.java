package com.rest.carrental.exception;

public class CarNotRentedException extends RuntimeException {
    public CarNotRentedException(Long id) {
        super(String.format("Car with id: %s isn't rented.", id));
    }
}
