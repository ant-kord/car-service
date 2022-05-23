package ru.softlogic.carservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.softlogic.carservice.model.Car;
import ru.softlogic.carservice.model.Order;
import ru.softlogic.carservice.service.CarService;
import ru.softlogic.carservice.service.OrderService;

@RestController
@Validated
@RequestMapping(value = "/api/v1/service")
public class CarServiceController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CarServiceController.class);

	@Autowired
    private OrderService orderService;
	
	@Autowired
    private CarService carService;

    @Autowired
    private Environment env;


    @GetMapping("/port")
    public String getPort(){
        return "Service is working at port : " + env.getProperty("local.server.port");
    }

    @PostMapping("/order/add")
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order order) {
        return new ResponseEntity<>(orderService.add(order), HttpStatus.CREATED);
    }
    
    @GetMapping("/order/list")
    public ResponseEntity<Page<Order>> getOrders(Pageable pageable) {
        return new ResponseEntity<Page<Order>>(orderService.getOrders(pageable), new HttpHeaders(), HttpStatus.OK); 
    }
    
    @GetMapping("/car/list")
    public ResponseEntity<Page<Car>> getCars(Pageable pageable) {
        return new ResponseEntity<Page<Car>>(carService.getCars(pageable), new HttpHeaders(), HttpStatus.OK); 
    }
    
    
}
