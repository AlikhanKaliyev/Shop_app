package com.example.shop_backend.domain.model.user;


import com.example.shop_backend.domain.model.comment.Comment;
import com.example.shop_backend.domain.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Product> products;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Comment> comments;
}
