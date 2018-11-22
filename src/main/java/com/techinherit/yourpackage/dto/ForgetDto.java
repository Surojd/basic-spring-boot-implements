package com.techinherit.yourpackage.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ForgetDto {

    @NotNull(message = "V002")
    private String username;

}
