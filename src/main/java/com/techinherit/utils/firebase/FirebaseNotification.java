package com.techinherit.utils.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techinherit.response.impl.ServiceResponseImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//@RestController
//@RequestMapping("firebase")
public class FirebaseNotification {

    @Value(value = "${fcm.api.url}")
    String url;
    @Value(value = "${fcm.api.key}")
    String key;
    @Autowired
    Headers headers;

    @Autowired
    ObjectMapper mapper;

//    @PostMapping
    public ServiceResponseImpl pushFCMNotification(FirebaseDto firebase) {
        ServiceResponseImpl response = new ServiceResponseImpl();
        try {
            Map<String, Object> json = new HashMap<>();
            json.put("to", firebase.getDeviceToken().trim());
            Map<String, String> info = new HashMap<>();
            info.put("title", firebase.getTitle()); // Notification title
            info.put("body", firebase.getBody()); // Notification body
            info.put("sound", "default"); // Notification Sound
            json.put("notification", info);
            if (firebase.getData() != null) {
                json.put("data", firebase.getData());
            }
            headers.setToken("key=" + key);
            Map maps = headers.sendPostRequest(url, mapper.writeValueAsString(json));
            response.setStatus(((int) maps.getOrDefault("success", 0)) == 1);
        } catch (Exception ex) {
            response.setStatus(false);
        }
        return response;
    }

}
