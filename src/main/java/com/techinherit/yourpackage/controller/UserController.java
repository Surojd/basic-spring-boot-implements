package com.techinherit.yourpackage.controller;

import com.techinherit.basic.response.MyException;
import com.techinherit.basic.response.ServiceResponse;
import com.techinherit.yourpackage.database.Users;
import com.techinherit.yourpackage.dto.UserCreateDto;
import com.techinherit.yourpackage.enums.ErrorCode;
import com.techinherit.yourpackage.enums.MessageCode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController extends ControllerObject {

    @GetMapping
    public ResponseEntity<?> getUsers() {
        ServiceResponse response = new ServiceResponse();
        List<Users> user = userDao.findAll();
        response.setData(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserCreateDto dto, Integer userId, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            throw new MyException(ErrorCode.V001, errors);
        }
        ServiceResponse response = new ServiceResponse(MessageCode.M002);
        userService.updateUser(dto, userId);
        return ResponseEntity.ok(response);
    }

}
