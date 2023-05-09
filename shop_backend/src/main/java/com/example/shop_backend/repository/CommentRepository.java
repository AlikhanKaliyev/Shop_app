package com.example.shop_backend.repository;

import com.example.shop_backend.domain.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(long product_id);
}
