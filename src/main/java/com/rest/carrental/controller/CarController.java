package com.rest.carrental.controller;

import com.rest.carrental.service.CarService;
import com.rest.carrental.entity.Car;
import com.rest.carrental.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> all() {
        return carService.all();
    }

    @GetMapping("/cars/{id}")
    public Car one(@PathVariable Long id) {
        return carService.one(id);
    }

    @PostMapping("/cars")
    public Car add(@RequestBody Car car) {
        return carService.add(car);
    }

    @DeleteMapping("/cars/{id}")
    public void remove(@RequestBody Long id) {
        carService.remove(id);
    }

    @PutMapping("/cars/{id}")
    public Car update(@RequestBody Car newCar, @PathVariable Long id) {
        return carService.update(newCar, id);
    }

    @PutMapping("cars/rent/{carId}")
    public Car rentCar(@RequestBody Customer customer, @PathVariable Long carId) {
        return carService.rentCar(customer, carId);
    }

    @PutMapping("cars/return/{carId}")
    public Car returnCar(@RequestBody Customer customer, @PathVariable Long carId) {
        return carService.returnCar(customer, carId);
    }

}
