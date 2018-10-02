package com.techinherit.response;

import com.techinherit.enums.ErrorCode;

public class MyException extends Exception {

    private ErrorCode errorCode;
    private Object errors;

    public MyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(ErrorCode errorCode, Object errors) {
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

}
