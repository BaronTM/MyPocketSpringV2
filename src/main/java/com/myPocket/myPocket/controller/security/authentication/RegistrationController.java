package com.myPocket.myPocket.controller.security.authentication;

import com.myPocket.myPocket.controller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/register")
@CrossOrigin
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest registrationRequest) throws Exception {
        Map<String, String> resultMap = new HashMap<String, String>();

        if (!registrationRequest.isValid()) {
            resultMap.put("result", "INCORRECT");
        } else if (userRepository.countByUsername(registrationRequest.getUsername()) > 0) {
            resultMap.put("result", "OCCUPIED");
        } else {
            userRepository.save(registrationRequest.toUser(passwordEncoder));
            resultMap.put("result", "OK");
        }

        return ResponseEntity.ok(resultMap);
    }

}
