package com.techinherit.basic.response;

import com.techinherit.yourpackage.enums.ErrorCode;
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

    ServiceResponse serviceResponse;

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<?> handleBusinessValidationException(Exception exception) {
        HttpStatus status = HttpStatus.OK;
        serviceResponse = new ServiceResponse();
        if (exception instanceof MyException) {
            MyException ex = (MyException) exception;
            serviceResponse.setData(ex.getData());
            serviceResponse.setErrorCode(ex.getErrorCode());
        } else if (exception instanceof BadCredentialsException) {
            BadCredentialsException bce = (BadCredentialsException) exception;
            serviceResponse.setErrorCode(ErrorCode.U002);
        } else if (exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
            serviceResponse.setErrorCode(ErrorCode.A403);
        } else if (exception instanceof AuthenticationException) {
            status = HttpStatus.FORBIDDEN;
            serviceResponse.setErrorCode(ErrorCode.A403);
        } else {
            serviceResponse.setErrorCode(ErrorCode.M001);
        }
        return new ResponseEntity<>(serviceResponse, status);
    }
}
