/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techinherit.yourpackage.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class VerificationDto extends ForgetDto {

    @Max(9999)
    @Min(1000)
    private int verificationCode;

}
