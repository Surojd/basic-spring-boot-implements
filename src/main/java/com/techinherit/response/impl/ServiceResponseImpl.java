package com.techinherit.response.impl;

import com.techinherit.tracking.enums.ErrorCode;
import com.techinherit.response.ServiceResponse;
import java.util.Date;

public class ServiceResponseImpl<E> implements ServiceResponse<E> {

    private boolean status = true;
    private ErrorCode errorCode;
    private String message;
    private E data;
    private Date date = new Date();

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.status = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setException(ErrorCode errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
        status = false;
    }
}
