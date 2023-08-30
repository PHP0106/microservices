package com.project.inventoryservice.repository;

import com.project.inventoryservice.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);
    List<Inventory> findAllByQuantityGreaterThan(Integer quantity);


}
