package com.techinherit.utils.firebase;

import javax.validation.constraints.NotNull;

public class FirebaseDto {

    @NotNull
    private String deviceToken;

    @NotNull
    private String title;
    @NotNull
    private String body;

    private Object data;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
