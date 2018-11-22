package com.techinherit.basic.response;

import com.techinherit.yourpackage.enums.ErrorCode;
import com.techinherit.yourpackage.enums.MessageCode;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ServiceResponse<T> {

    private boolean status = true;
    private ErrorCode errorCode;
    private MessageCode message;
    private T data;
    private Date date = new Date();

    public ServiceResponse(MessageCode message) {
        this.message = message;
        this.errorCode = null;
        this.status = true;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
        this.status = false;
    }

    public void setData(T data) {
        this.data = data;
        this.status = true;
    }
}
