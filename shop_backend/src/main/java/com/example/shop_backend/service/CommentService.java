package com.example.shop_backend.service;

import com.example.shop_backend.domain.dto.CommentCreateDTO;
import com.example.shop_backend.domain.model.comment.Comment;
import com.example.shop_backend.domain.model.product.Product;
import com.example.shop_backend.domain.model.user.User;
import com.example.shop_backend.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Comment postComment(CommentCreateDTO commentCreateDTO, Authentication authentication) {
        Long _userId = ((User) authentication.getPrincipal()).getId();
        Optional<Product> product = productRepository.findById(commentCreateDTO.getProductId());
        Optional<User> user = userRepository.findById(_userId);
        Comment _comment = new Comment(
                commentCreateDTO.getId(),
                commentCreateDTO.getText(),
                commentCreateDTO.getRating(),
                product.get(),
                user.get()
        );
        commentRepository.save(_comment);
        return _comment;
    }
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findAll().forEach(comments::add);
        return comments;
    }
    public List<Comment> getCommentsByProduct(long product_id) {
        List<Comment> comments = new ArrayList<Comment>();
        commentRepository.findByProductId(product_id).forEach(comments::add);
        return comments;
    }
}
