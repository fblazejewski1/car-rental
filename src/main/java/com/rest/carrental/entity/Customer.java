package com.rest.carrental.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "customer")
    private Set<Car> rentedCars = new HashSet<>();

    @NotNull
    private String name;

    public Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCar(Car car) {
        rentedCars.add(car);
        car.setCustomer(this);
    }

    public void removeCar(Car car) {
        rentedCars.remove(car);
        car.setCustomer(null);
    }

    @Override
    public String toString() {
        return String.format("{id=%s, name=%s, rentedCars=%s}", id, name, rentedCars);
    }
}
