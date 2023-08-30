package com.project.orderservice.service;

import com.project.orderservice.dto.OrderDTO;
import org.springframework.stereotype.Service;

public interface OrderService {
    public void placeOrder(OrderDTO orderDTO);

    public OrderDTO getOrderDTOById(Long id);
}
