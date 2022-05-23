package ru.softlogic.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.softlogic.carservice.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
