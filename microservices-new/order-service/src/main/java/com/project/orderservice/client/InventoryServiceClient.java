package com.project.orderservice.client;


import com.project.inventoryservice.domain.Inventory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:8082")
public interface InventoryServiceClient {

    @GetMapping(value = {"/api/inventory/skuCodesValid"})
    List<Inventory> getSkuCodesValid();

    @PutMapping("/api/inventory/setQuantity")
    void setQuantity(@RequestBody List<Inventory> inventoryList);

    @GetMapping("/api/inventory/getQuantityBySkuCode")
    Integer getQuantityBySkuCode(@RequestParam("skuCode") String skuCode);

}
