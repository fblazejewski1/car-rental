package com.rest.carrental;

import com.rest.carrental.entity.Car;
import com.rest.carrental.entity.Customer;
import com.rest.carrental.repository.CarRepo;
import com.rest.carrental.repository.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepo customerRepo, CarRepo carRepo) {
        return args -> {
            log.info("Generating customers db");
            log.info("added: " + customerRepo.save(new Customer("Jan Kowalski")));
            log.info("added: " + customerRepo.save(new Customer("Michał Nowak")));

            log.info("Generating cars db");
            log.info("added: " + carRepo.save(new Car("Dodge", "Viper")));
            log.info("added: " + carRepo.save(new Car("Lamborghini", "Diablo")));
            log.info("added: " + carRepo.save(new Car("Lamborghini", "Murcielago")));
            log.info("added: " + carRepo.save(new Car("BMW", "m3")));
            log.info("added: " + carRepo.save(new Car("BMW", "i8")));
        };
    }

}
