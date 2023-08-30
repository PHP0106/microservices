package com.project.inventoryservice.controller;


import com.project.inventoryservice.domain.Inventory;
import com.project.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam ("skuCode") String skuCode ){
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("/getQuantityBySkuCode")
//    @ResponseStatus(HttpStatus.OK)
    public Integer getQuantityBySkuCode(@RequestParam("skuCode") String skuCode){
        return inventoryService.getQuantitySkuCode(skuCode);
    }

    @PutMapping("/setQuantity")
//    @ResponseStatus(HttpStatus.OK)
    public void setQuantity(@RequestBody List<Inventory> inventoryList){
        inventoryService.setQuantity(inventoryList);
    }

    @GetMapping("/skuCodesValid")
//    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getSkuCodesValid(){
        return inventoryService.skuCodesListInStocks();
    }
}
