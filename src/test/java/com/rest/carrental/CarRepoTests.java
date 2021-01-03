package com.rest.carrental;

import com.rest.carrental.entity.Car;
import com.rest.carrental.entity.Customer;
import com.rest.carrental.repository.CarRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepoTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CarRepo carRepo;

    @Test
    public void addCar() {
        Car car = new Car("car", "model");
        entityManager.persist(car);
        entityManager.flush();

        List<Car> cars = carRepo.findAll();

        assertThat(cars)
                .isNotEmpty()
                .contains(car)
                .doesNotContainNull();
    }

    @Test
    public void removeCar() {
        Car car = new Car("car", "model");
        Car otherCar = new Car("othercar", "model");
        entityManager.persist(car);
        entityManager.persist(otherCar);
        entityManager.flush();

        entityManager.remove(car);
        entityManager.flush();

        List<Car> cars = carRepo.findAll();

        assertThat(cars)
                .isNotEmpty()
                .doesNotContain(car)
                .contains(otherCar)
                .doesNotContainNull();
    }

//    @Test
//    public void rentCar() {
//        Customer customer = new Customer("Jan");
//        Car car = new Car("car", "model");
////        customer.addCar(car);
//
//        entityManager.persist(customer);
//        entityManager.persist(car);
//        entityManager.flush();
//        customer.addCar(car);
//
////        Car c = carRepo.getOne(1L);
////        assertThat(c).isNotNull();
//        assertThat(customer).isNotNull();
//    }
}
