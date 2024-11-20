package com.ecom.proj.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long Id;
    private String name;
    private Integer categoryId;
    private double price;
    private double weight;
    private String description;
    private String imageName;
}
