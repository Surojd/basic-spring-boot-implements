package com.techinherit.utils.firebase;

import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Headers {

    HttpHeaders headers = new HttpHeaders();
    private RestTemplate restTemplate = new RestTemplate();

    public Headers() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    void setToken(String key) {
        headers.set("Authorization", key);
    }

    public Map sendPostRequest(String url, Object requestJson) {
        HttpEntity entity = new HttpEntity(requestJson, headers);
        return restTemplate.postForObject(url, entity, Map.class);
    }
}
