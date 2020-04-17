package com.myPocket.myPocket.controller.security.authentication;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class AuthenticationRequest {
    private String userName;
    private String password;
}
