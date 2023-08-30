package com.project.orderservice.controller;


import com.project.orderservice.dto.OrderDTO;
import com.project.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placedOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
        return "Order Placed Successfully";
    }

    public String fallbackMethod(OrderDTO orderDTO, RuntimeException runtimeException ) {
        return "Oops! Something went wrong, please order after some time!";
    }

    @GetMapping("/allOrderById")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getAllOrderById(@RequestParam Long orderId) {
        return orderService.getOrderDTOById(orderId);
    }
}
