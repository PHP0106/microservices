package com.project.productservice.service;

import com.project.productservice.dto.ProductDTO;
import com.project.productservice.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    public ProductDTO createProduct(ProductDTO productDTO);
    public ProductDTO updateProductById(ProductDTO productDTO);

    public List<ProductDTO> getAllProducts();
    public ProductDTO getProductById(String id);
    public List<ProductDTO> findProductByCondition(String name, Float price);
    public ProductDTO deleteProductById(String id);
}
