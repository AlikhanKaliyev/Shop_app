package com.example.shop_backend.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ProductCreateDTO {

    private long id;
    private String productName;
    private String productDescription;
    private String productImage;

}
