package com.project.orderservice.service;

import com.project.orderservice.dto.OrderDTO;
import com.project.orderservice.dto.OrderLineItemsDTO;

public interface OrderLineItemsService {
    public void placeOrderLineItemsService(OrderLineItemsDTO orderLineItemsDTO, Long orderId);
}
