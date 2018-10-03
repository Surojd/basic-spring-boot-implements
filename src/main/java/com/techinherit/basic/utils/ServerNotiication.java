package com.techinherit.basic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServerNotiication {

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    public void push(String body) {
        try {
            this.brokerMessagingTemplate.convertAndSend("/topic/notification", body);
        } catch (Exception ex) {
        }
    }

}
