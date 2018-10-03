package com.techinherit.basic.controller;

import com.techinherit.basic.dto.LoginDto;
import com.techinherit.basic.enums.ErrorCode;
import com.techinherit.basic.utils.DeviceVerificationUtil;
import com.techinherit.basic.response.MyException;
import com.techinherit.basic.response.impl.ServiceResponseImpl;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    private DeviceVerificationUtil deviceVerificationUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("auth")
    public ServiceResponseImpl<String> createAuthenticationToken(@RequestBody @Valid LoginDto dto, BindingResult result) throws Exception {
        ServiceResponseImpl<String> response = new ServiceResponseImpl<>();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            throw new MyException(ErrorCode.V001, errors);
        } else {
            authencate(dto.getUsername(), dto.getPassword());
            String AuthToken = deviceVerificationUtil.generateToken(dto.getUsername(), dto.getPassword());
            response.setData(AuthToken);
            return response;
        }
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
}
