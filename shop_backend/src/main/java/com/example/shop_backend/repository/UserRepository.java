package com.example.shop_backend.repository;

import com.example.shop_backend.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
