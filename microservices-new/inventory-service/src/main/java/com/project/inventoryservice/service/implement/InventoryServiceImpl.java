package com.project.inventoryservice.service.implement;

import com.project.inventoryservice.domain.Inventory;
import com.project.inventoryservice.repository.InventoryRepository;
import com.project.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    @Override
    public List<Inventory> skuCodesListInStocks() {
        List<Inventory> inventoryList = inventoryRepository.findAllByQuantityGreaterThan(0);
        return inventoryList;
    }

    @Override
    public Integer getQuantitySkuCode(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElse(null);
        if (inventory != null) {
            return inventory.getQuantity();
        }
        return 0;
    }

    @Override
    @Transactional
    public void setQuantity(List<Inventory> inventoryList) {

        inventoryList.forEach(inventory ->{
            Inventory inv = inventoryRepository.findBySkuCode(inventory.getSkuCode()).orElse(null);
            if (inv != null) {
                inv.setQuantity(inventory.getQuantity());
                inventoryRepository.save(inv);
            }
        });
    }
}
