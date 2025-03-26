package com.test.dream_shop.request;

import java.math.BigDecimal; 


import com.test.dream_shop.model.Category;

import lombok.Data;


@Data
public class AddProductRequest {

    // private Long id;
    
    private String name;

    private String brand;

    private String description;

    private Integer inventory;

    private BigDecimal price;
    
    private Category category;  

}
