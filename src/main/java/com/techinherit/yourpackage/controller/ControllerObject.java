package com.techinherit.yourpackage.controller;

import com.techinherit.yourpackage.dao.UserRepository;
import com.techinherit.yourpackage.service.EmailService;
import com.techinherit.yourpackage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ControllerObject {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userDao;

    @Autowired
    public EmailService emailService;

    
    @Autowired
    public PasswordEncoder passwordEncoder;
}
