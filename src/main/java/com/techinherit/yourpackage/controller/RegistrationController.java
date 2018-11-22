package com.techinherit.yourpackage.controller;

import com.techinherit.basic.response.MyException;
import com.techinherit.basic.response.ServiceResponse;
import com.techinherit.yourpackage.dto.ShopCreateDto;
import com.techinherit.yourpackage.dto.UserCreateDto;
import com.techinherit.yourpackage.enums.ErrorCode;
import com.techinherit.yourpackage.enums.MessageCode;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RegistrationController extends ControllerObject {

    @PostMapping(value = "/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid UserCreateDto userForm, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            throw new MyException(ErrorCode.V001, errors);
        }
        ServiceResponse response = new ServiceResponse(MessageCode.M002);
        userService.createUser(userForm);
        return ResponseEntity.ok(response);
    }

}
