package com.rest.carrental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.carrental.service.CarService;
import com.rest.carrental.controller.CarController;
import com.rest.carrental.entity.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
public class CarControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllCars() throws Exception {
        Car car = new Car(2L, "car", "model");

        List<Car> allCars = Arrays.asList(car);

        when(service.all()).thenReturn(allCars);

        mvc.perform(get("/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].model", is(car.getModel())));
    }

    @Test
    public void addCar() throws Exception {
        Car returned = new Car(2L, "car", "model");
        Car car = new Car("car", "model");

        when(service.add(any(Car.class))).thenReturn(returned);

        mvc.perform(post("/cars")
                .content(mapper.writeValueAsString(car))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.brand", is(returned.getBrand())))
                .andExpect(jsonPath("$.model", is(returned.getModel())));
    }
}
