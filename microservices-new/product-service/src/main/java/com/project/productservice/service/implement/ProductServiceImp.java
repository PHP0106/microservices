package com.project.productservice.service.implement;

import com.project.productservice.domain.Product;
import com.project.productservice.dto.ProductDTO;
import com.project.productservice.dto.ProductResponse;
import com.project.productservice.exception.CustomException;
import com.project.productservice.exception.define.ErrorCode;
import com.project.productservice.exception.define.ErrorMessage;
import com.project.productservice.repository.ProductRepository;
import com.project.productservice.service.ProductService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        productRepository.save(product);
        return this.mapToProductDTO(product);
    }

    @Override
    public ProductDTO updateProductById(ProductDTO productDTO) {
        Optional<Product> product = productRepository.getProductById(productDTO.getId());
        if (!product.isPresent())
            throw new CustomException(ErrorMessage.PRODUCT_NOT_FOUND, ErrorCode.notFound);
        Product newProduct = product.get().builder().id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        productRepository.save(newProduct);
        return this.mapToProductDTO(newProduct);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductDTO).toList();
    }

    @Override
    public ProductDTO getProductById(String id) {
        Optional<Product> product = productRepository.getProductById(id);
        if (!product.isPresent()) {
            throw new CustomException(ErrorMessage.PRODUCT_NOT_FOUND, ErrorCode.notFound);
        }
        return this.mapToProductDTO(product.get());
    }

    @Override
    public List<ProductDTO> findProductByCondition(String name, Float price) {
        List<Product> productList = productRepository.getProductsByNameOrPrice(name, price);
        return productList.stream().map(this::mapToProductDTO).toList();
    }

    @Override
    public ProductDTO deleteProductById(String id) {
        Optional<Product> product = productRepository.getProductById(id);
        if (!product.isPresent())
            throw new CustomException(ErrorMessage.PRODUCT_NOT_FOUND, ErrorCode.notFound);
        productRepository.delete(product.get());
        return this.mapToProductDTO(product.get());
    }

    private ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
