package com.example.shop_backend.controller;

import com.example.shop_backend.domain.model.comment.Comment;
import com.example.shop_backend.domain.dto.CommentCreateDTO;
import com.example.shop_backend.domain.model.error.ErrorResponse;
import com.example.shop_backend.domain.model.product.Product;
import com.example.shop_backend.domain.model.user.User;
import com.example.shop_backend.repository.CommentRepository;
import com.example.shop_backend.repository.ProductRepository;
import com.example.shop_backend.repository.UserRepository;
import com.example.shop_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping()
    public ResponseEntity postComment(@RequestBody CommentCreateDTO commentCreateDTO, Authentication authentication) {
        try {
            Comment comment = commentService.postComment(commentCreateDTO, authentication);
            return new ResponseEntity(comment,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse("Some properties are missing"), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping()
    public ResponseEntity getComments() {
        try {
            List<Comment> comments = commentService.getComments();
            return new ResponseEntity(comments,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{product_id}")
    public ResponseEntity getCommentsByProduct(@PathVariable("product_id") long product_id) {
        try {
            List<Comment> comments = commentService.getCommentsByProduct(product_id);
            return new ResponseEntity(comments,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse("No products with such id"),HttpStatus.BAD_REQUEST);
        }
    }
}
