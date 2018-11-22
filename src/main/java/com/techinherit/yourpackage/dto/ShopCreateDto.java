package com.techinherit.yourpackage.dto;

import lombok.Data;

@Data
public class ShopCreateDto extends UserCreateDto{

    private String landlineNumber;
    private String shopName;
    private String shopAddress;
}
