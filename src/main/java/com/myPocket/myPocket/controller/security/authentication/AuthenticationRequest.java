package com.myPocket.myPocket.controller.security.authentication;

import com.myPocket.myPocket.model.entities.User;
import com.myPocket.myPocket.model.utils.Validation;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;

    public boolean isUsernameValid() {
        return (Validation.getLoginPattern().matcher(username).matches() || Validation.getEmailPattern().matcher(username).matches());
    }

    public boolean isPasswordValid() {
        return Validation.getPasswordPattern().matcher(password).matches();
    }

    public boolean isValid() {
        return isUsernameValid() && isPasswordValid();
    }

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password));
    }
}
