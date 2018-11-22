package com.techinherit.yourpackage.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PhoneNumbers {
    @Id
    private String phoneNumber;
    private Integer verificationCode;
    private Long signupDate;
}
