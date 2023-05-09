package com.example.shop_backend.domain.model.product;

import com.example.shop_backend.domain.model.comment.Comment;
import com.example.shop_backend.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private long id;

    @NotNull(message = "product_name is mandatory")
    @Column(name = "product_name")
    private String productName;

    @NotNull(message = "product_description is mandatory")
    @Column(name = "product_description")
    private String productDescription;

    @NotNull(message = "product_image is mandatory")
    @Column(name = "product_image")
    private String productImage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id",referencedColumnName = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_on")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Comment> comments;

    public Product(
            long id,
            String productName,
            String productDescription,
            String productImage,
            User user
    ) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.user = user;
    }

}
