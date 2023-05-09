package com.example.shop_backend.domain.model.comment;

import com.example.shop_backend.domain.model.product.Product;
import com.example.shop_backend.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    private int rating;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_on")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdOn;

    public Comment(
            long id,
            String text,
            int rating,
            Product product,
            User user
    ) {
        this.id = id;
        this.text = text;
        this.rating = rating;
        this.product = product;
        this.user = user;
    }

}
