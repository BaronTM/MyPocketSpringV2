package com.myPocket.myPocket.controller.pages.app;

import com.myPocket.myPocket.controller.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class App {

    @Autowired
    private JsonResponse jsonResponse;

    @GetMapping(value = "/app")
    public String app(Authentication authentication) {
        return jsonResponse.getJsonResponseWithToken(authentication.getName(), authentication.getName());
    }

}
