package com.example.shop_backend.service;

import com.example.shop_backend.domain.dto.ProductCreateDTO;
import com.example.shop_backend.domain.model.product.Product;
import com.example.shop_backend.domain.model.user.User;
import com.example.shop_backend.repository.ProductRepository;
import com.example.shop_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product createProduct(ProductCreateDTO productCreateDTO, Authentication authentication) {
        Long _userId = ((User) authentication.getPrincipal()).getId();
        Optional<User> _user = userRepository.findById(_userId);
        Product _product = new Product(
                productCreateDTO.getId(),
                productCreateDTO.getProductName(),
                productCreateDTO.getProductDescription(),
                productCreateDTO.getProductImage(),
                _user.get()
        );
        productRepository.save(_product);
        return _product;
    }
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();
        productRepository.findAll().forEach(products::add);
        return products;
    }
}
