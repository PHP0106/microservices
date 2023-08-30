package com.project.productservice.service;

import com.project.productservice.dto.ProductDTO;
import com.project.productservice.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    public void createProduct(ProductDTO productDTO);

    List<ProductResponse> getAllProducts();
}
