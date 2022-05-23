package ru.softlogic.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.softlogic.carservice.model.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

}
