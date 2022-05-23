package ru.softlogic.carservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.softlogic.carservice.model.Car;


public interface CarService {

	Page<Car> getCars(Pageable pageable);
}
