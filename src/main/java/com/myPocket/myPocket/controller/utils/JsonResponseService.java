package com.myPocket.myPocket.controller.utils;

import com.google.gson.Gson;
import com.myPocket.myPocket.controller.services.UserRepositoryUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JsonResponseService {

    private Gson gson;
    private JwtUtil jwtTokenUtil;
    private UserRepositoryUserDetailService userDetailService;

    @Autowired
    public JsonResponseService(Gson gson, JwtUtil jwtTokenUtil, UserRepositoryUserDetailService userDetailService) {
        this.gson = gson;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailService = userDetailService;
    }

    public String getJsonResponseWithToken(Object object, String username) {
        Map<String, Object> responseObject = new HashMap<>();

        String jwt = getNewToken(username);
        responseObject.put("jwt", jwt);

        responseObject.put("data", object);

        return gson.toJson(responseObject);
    }

    private String getNewToken(String username) {
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return jwt;
    }

}
