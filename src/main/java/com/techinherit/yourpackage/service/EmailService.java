package com.techinherit.yourpackage.service;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailService {

    void sendEmailMessage(String to, String subject, String text);

}
