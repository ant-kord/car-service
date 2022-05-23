package ru.softlogic.carservice.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ru.softlogic.carservice.model.Order;

public interface OrderService {

	Page<Order> getOrders(Pageable pageable);
	Order add(Order order);
}
