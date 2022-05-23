package ru.softlogic.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.softlogic.carservice.model.Car;
import ru.softlogic.carservice.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
    private CarRepository carRepository;

	@Override
	public Page<Car> getCars(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

}
