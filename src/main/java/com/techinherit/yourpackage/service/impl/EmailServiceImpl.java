/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techinherit.yourpackage.service.impl;

import com.techinherit.yourpackage.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendEmailMessage(String to, String subject, String text) {
        new Thread(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("no-reply@gsewa.com.np");
                message.setTo(to);
                message.setSubject(subject);
                message.setText(text);
                emailSender.send(message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

}
