package ru.softlogic.carservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.softlogic.carservice.model.Order;
import ru.softlogic.carservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;

	@Transactional
	@Override
	public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable); 
	}

	@Transactional
	@Override
	public Order add(Order order) {
		return orderRepository.save(order);
	}

}
