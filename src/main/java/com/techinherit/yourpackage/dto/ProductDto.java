package com.techinherit.yourpackage.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String productName;
    private String description;
    private String featureList;
    private Integer defaultQuantity;
    private String sku;
    private String status;
    private String mainMaterial;
    private String defaultColor;
    private Double defaultPrice;
    private Integer discountRate;
    private String defaultSize;
    private String model;
    private Boolean options;
    private String boxFeatures;
    private Integer brandId;
    private Integer categoyId;
}
