package com.project.orderservice.service.implement;

import com.project.inventoryservice.domain.Inventory;
import com.project.orderservice.client.InventoryServiceClient;
import com.project.orderservice.event.OrderPlacedEvent;
import com.project.orderservice.repository.OrderLineItemsRepository;
import com.project.orderservice.repository.OrderRepository;
import com.project.orderservice.domain.Order;
import com.project.orderservice.domain.OrderLineItems;
import com.project.orderservice.dto.OrderDTO;
import com.project.orderservice.dto.OrderLineItemsDTO;
import com.project.orderservice.service.OrderLineItemsService;
import com.project.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemsService orderLineItemsService;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private final ModelMapper modelMapper;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate kafkaTemplate;
    @Override
    @Transactional
    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        //Stock

        List<Inventory> inventoryList = inventoryServiceClient.getSkuCodesValid();

        List<String> skuCodesValid = inventoryList.stream().map(Inventory::getSkuCode).toList();

        List<Inventory> remainingList = new ArrayList<>();

        orderDTO.getOrderLineItemsDTOList().forEach(orderLineItemsDTO -> {
            if (skuCodesValid.contains(orderLineItemsDTO.getSkuCode())) {

                Integer orderQuantity = orderLineItemsDTO.getQuantity();
                Integer stockQuantity = inventoryServiceClient.getQuantityBySkuCode(orderLineItemsDTO.getSkuCode());

               if ( stockQuantity < orderQuantity) {
                   throw new IllegalArgumentException("Product with skuCode: " + orderLineItemsDTO.getSkuCode() + " not enough quantity left" );
               } else {
                   Inventory inventory = new Inventory();
                   inventory.setSkuCode(orderLineItemsDTO.getSkuCode());
                   inventory.setQuantity(stockQuantity - orderQuantity);
                   remainingList.add(inventory);
               }
            } else {
                throw new IllegalArgumentException("Product with skuCode: " + orderLineItemsDTO.getSkuCode() + " is not in stock" );
            }
        });

        inventoryServiceClient.setQuantity(remainingList);

        Order finalOrder = orderRepository.save(order);
//        List<OrderLineItems> orderLineItemsList =orderDTO.getOrderLineItemsDTOList()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
        orderDTO.getOrderLineItemsDTOList()
                .forEach(orderLineItemsDTO -> orderLineItemsService.placeOrderLineItemsService(orderLineItemsDTO, finalOrder.getId()));
//        kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
    }

    @Override
    public OrderDTO getOrderDTOById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            OrderDTO orderDTO = new OrderDTO();
            List<OrderLineItems> orderLineItemsList = orderLineItemsRepository.findAllByOrderId(id);
            List<OrderLineItemsDTO> orderLineItemsDTOList = new ArrayList<>();
            orderLineItemsList.forEach(orderLineItems -> {
                orderLineItemsDTOList.add(modelMapper.map(orderLineItems, OrderLineItemsDTO.class));
            });
            orderDTO.setOrderLineItemsDTOList(orderLineItemsDTOList);
            return orderDTO;
        }
        return null;
    }

//    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDTO) {
//        OrderLineItems orderLineItems = new OrderLineItems();
//        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
//        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
//        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
//        orderLineItems.setOrder_id(orderLineItemsDTO.getOrder_id());
//        return orderLineItems;
//    }
}
