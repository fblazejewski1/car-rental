package com.rest.carrental.service;

import com.rest.carrental.entity.*;
import com.rest.carrental.exception.*;
import com.rest.carrental.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepo carRepo;

    @Autowired
    CustomerRepo customerRepo;

    public List<Car> all() {
        return carRepo.findAll();
    }

    public Car one(Long id) {
        return carRepo.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public Car add(Car car) {
        return carRepo.save(car);
    }

    public void remove(Long id) {
        carRepo.deleteById(id);
    }

    public Car update(Car newCar, Long id) {
        return carRepo.findById(id)
                .map(car -> {
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    return carRepo.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public Car rentCar(Customer customer, Long carId) {
        return carRepo.findById(carId)
                .map(car -> {
                    if (car.getCustomer() != null)
                        throw new CarAlreadyRentedException(carId);

                    Customer dbCustomer = customerRepo.findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException(customer.getId()));

                    dbCustomer.addCar(car);
                    customerRepo.save(dbCustomer);
                    return carRepo.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(carId));
    }

    public Car returnCar(Customer customer, Long carId) {
        return carRepo.findById(carId)
                .map(car -> {
                    if (car.getCustomer() == null)
                        throw new CarNotRentedException(carId);

                    Customer dbCustomer = customerRepo.findById(customer.getId()).orElseThrow(() -> new CustomerNotFoundException(customer.getId()));

                    dbCustomer.removeCar(car);
                    customerRepo.save(dbCustomer);
                    return carRepo.save(car);
                })
                .orElseThrow(() -> new CarNotFoundException(carId));
    }

}
