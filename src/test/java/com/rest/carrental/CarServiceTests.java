package com.rest.carrental;

import com.rest.carrental.service.CarService;
import com.rest.carrental.entity.Car;
import com.rest.carrental.entity.Customer;
import com.rest.carrental.exception.CarAlreadyRentedException;
import com.rest.carrental.repository.CarRepo;
import com.rest.carrental.repository.CustomerRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CarServiceTests {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepo carRepo;

    @Mock
    private CustomerRepo customerRepo;

    private Car car;
    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer(1L, "Jan");
        car = new Car(2L, "car", "model");

        when(carRepo.save(any(Car.class))).thenReturn(car);
        when(customerRepo.findById(1L)).thenReturn(java.util.Optional.ofNullable(customer));
        when(carRepo.findById(2L)).thenReturn(java.util.Optional.ofNullable(car));
    }


    @Test
    public void addCar() {
        Car returned = carService.add(car);

        assertThat(returned.getModel()).isEqualTo(car.getModel());
    }

    @Test(expected = CarAlreadyRentedException.class)
    public void carAlreadyRented() {
        carService.rentCar(customer, 2L);
        carService.rentCar(customer, 2L);
    }
}
