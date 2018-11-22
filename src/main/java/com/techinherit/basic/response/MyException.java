package com.techinherit.basic.response;

import com.techinherit.yourpackage.enums.ErrorCode;
import lombok.Data;

@Data
public class MyException extends Exception {

    private ErrorCode errorCode;
    private Object data;

    public MyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MyException(ErrorCode errorCode, Object data) {
        this.errorCode = errorCode;
        this.data = data;
    }

}
