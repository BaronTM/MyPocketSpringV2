package com.myPocket.myPocket.controller.app;

import com.myPocket.myPocket.controller.utils.JsonResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/app")
public class App {

    private
    @Autowired JsonResponseService jsonResponse;

    @GetMapping
    public String app(Authentication authentication) {
        return jsonResponse.getJsonResponseWithToken(authentication.getName(), authentication.getName());
    }
}
