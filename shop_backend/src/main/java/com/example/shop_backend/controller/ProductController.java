package com.example.shop_backend.controller;

import com.example.shop_backend.domain.model.error.ErrorResponse;
import com.example.shop_backend.domain.dto.ProductCreateDTO;
import com.example.shop_backend.domain.model.product.Product;
import com.example.shop_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping()
    public ResponseEntity createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        try {
            Product product = productService.createProduct(productCreateDTO);
            return new ResponseEntity(product,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse("Some properties are missing"), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping()
    public ResponseEntity getProducts() {
        try {
            List<Product> products = productService.getProducts();
            return new ResponseEntity(products,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
