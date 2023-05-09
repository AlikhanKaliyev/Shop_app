package com.example.shop_backend.domain.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_rating_id")
    private long id;

    @Column(name = "number_of_ratings")
    private int number_of_ratings;

    @Column(name = "sum_of_ratings")
    private int sum_of_ratings;

}
