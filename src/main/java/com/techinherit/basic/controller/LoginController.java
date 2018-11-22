package com.techinherit.basic.controller;

import com.techinherit.yourpackage.dto.LoginDto;
import com.techinherit.yourpackage.enums.ErrorCode;
import com.techinherit.basic.utils.DeviceVerificationUtil;
import com.techinherit.basic.response.MyException;
import com.techinherit.basic.response.ServiceResponse;
import com.techinherit.yourpackage.controller.ControllerObject;
import com.techinherit.yourpackage.dto.ForgetDto;
import com.techinherit.yourpackage.dto.ResetPasswordDto;
import com.techinherit.yourpackage.dto.VerificationDto;
import com.techinherit.yourpackage.enums.MessageCode;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController extends ControllerObject {

    @Autowired
    private DeviceVerificationUtil deviceVerificationUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("phone/{phoneNumber}")
    public ResponseEntity<?> getPhoneNumber(@PathVariable String phoneNumber) throws Exception{
        ServiceResponse response = new ServiceResponse();
        userService.findByUsername(phoneNumber);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("phone/{phoneNumber}/sendcode")
    public ResponseEntity<?> getPhoneNumberSendCode(@PathVariable String phoneNumber) throws Exception{
        ServiceResponse response = new ServiceResponse();
        userService.sendVerificationCode(phoneNumber);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("auth")
    public ServiceResponse createAuthenticationToken(@RequestBody @Valid LoginDto dto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            throw new MyException(ErrorCode.V001, errors);
        }
        ServiceResponse response = new ServiceResponse(MessageCode.M001);
        authencate(dto.getUsername(), dto.getPassword());
        String AuthToken = deviceVerificationUtil.generateToken(dto.getUsername(), dto.getPassword());
        response.setData(AuthToken);
        return response;
    }

    public boolean authencate(String username, String password) throws UsernameNotFoundException, AuthenticationException {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username,
                password
        );
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }

    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword(@RequestBody @Valid ForgetDto dto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new MyException(ErrorCode.U003);
        }
        ServiceResponse response = new ServiceResponse(MessageCode.M004);
        userService.forgotPassword(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody @Valid VerificationDto dto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new MyException(ErrorCode.V007);
        }
        ServiceResponse sr = new ServiceResponse<>(MessageCode.M006);
        userService.verifyUser(dto);
        return ResponseEntity.ok(sr);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordDto dto, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            throw new MyException(ErrorCode.V005);
        }
        ServiceResponse sr = new ServiceResponse<>(MessageCode.M008);
        userService.resetPassword(dto);
        return ResponseEntity.ok(sr);
    }

}
