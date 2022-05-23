package ru.softlogic.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.softlogic.carservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
