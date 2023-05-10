package com.example.shop_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentCreateDTO {
    private long id;
    private String text;
    private int rating;
    private long productId;
}
