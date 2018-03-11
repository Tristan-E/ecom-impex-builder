package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.controller;

import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.dao.CarRepository;
import com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder.model.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class CarController {
    private CarRepository repository;

    public CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/cars")
    public Collection<Car> coolCars() {
        Collection<Car> cars = repository.findAll();
        if(cars.isEmpty()) {
            Car car = new Car();
            car.setName("BADIBOU");
            repository.save(car);
        }

        return repository.findAll();
    }

}
