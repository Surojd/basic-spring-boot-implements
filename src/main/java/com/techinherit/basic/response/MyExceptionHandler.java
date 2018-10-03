package com.techinherit.basic.response;

import com.techinherit.basic.enums.ErrorCode;
import com.techinherit.basic.response.impl.ServiceResponseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Controller
public class MyExceptionHandler {

    ServiceResponseImpl serviceResponse;

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<?> handleBusinessValidationException(Exception exception) {
        HttpStatus status = HttpStatus.OK;
        serviceResponse = new ServiceResponseImpl();
        if (exception instanceof MyException) {
            MyException ex = (MyException) exception;
            serviceResponse.setErrorCode(ex.getErrorCode());
            if (ex.getErrors() != null) {
                serviceResponse.setData(ex.getErrors());
            }
        } else if (exception instanceof BadCredentialsException) {
            BadCredentialsException bce = (BadCredentialsException) exception;
            serviceResponse.setErrorCode(ErrorCode.U010);
        } else if (exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
            serviceResponse.setErrorCode(ErrorCode.A403);
        } else if (exception instanceof AuthenticationException) {
            status = HttpStatus.FORBIDDEN;
            serviceResponse.setErrorCode(ErrorCode.A403);
        } else {
            exception.printStackTrace();
            serviceResponse.setException(ErrorCode.M001, exception.getMessage());
        }
        return new ResponseEntity<>(serviceResponse, status);
    }
}
