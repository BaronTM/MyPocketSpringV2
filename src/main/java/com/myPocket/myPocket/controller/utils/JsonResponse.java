package com.myPocket.myPocket.controller.utils;

import com.google.gson.Gson;
import com.myPocket.myPocket.controller.services.PocketUserDetailService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class JsonResponse {

    @Autowired
    private PocketUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private Gson gson;

    public String getJsonResponseWithToken(Object object, String userName) {
        Map<String, Object> responseObject = new HashMap<>();

        String jwt = getNewToken(userName);
        responseObject.put("jwt", jwt);

        responseObject.put("data", object);

        return gson.toJson(responseObject);
    }

    private String getNewToken(String userName) {
        final UserDetails userDetails = userDetailService.loadUserByUsername(userName);
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return jwt;
    }

}
