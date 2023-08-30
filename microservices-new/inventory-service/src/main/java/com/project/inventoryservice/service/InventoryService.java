package com.project.inventoryservice.service;

import com.project.inventoryservice.domain.Inventory;

import java.util.List;

public interface InventoryService {

    public boolean isInStock(String skuCode);

    public List<Inventory> skuCodesListInStocks();

    public Integer getQuantitySkuCode(String skuCode);

    public void setQuantity(List<Inventory> inventoryList);



}
