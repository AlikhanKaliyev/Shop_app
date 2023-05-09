package com.example.shop_backend.repository;

import com.example.shop_backend.domain.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
