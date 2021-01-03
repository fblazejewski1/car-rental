package com.rest.carrental.entity;

import javax.persistence.*;

@Entity
public class Car {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne @JoinColumn(name = "customer_id")
    private Customer customer;

    private String brand;
    private String model;

    public Car() {}

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Car(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return String.format("{id=%s, customer=%s, brand=%s, model=%s}", id, customer, brand, model);
    }
}
