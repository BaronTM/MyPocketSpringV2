package com.myPocket.myPocket.controller.utils;

import com.google.gson.Gson;
import com.myPocket.myPocket.controller.repository.UserRepository;
import com.myPocket.myPocket.controller.services.UserRepositoryUserDetailService;
import com.myPocket.myPocket.environment.TestEnvironment;
import com.myPocket.myPocket.model.entities.User;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Import(JsonResponseServiceTestConfiguration.class)
@SpringBootTest(classes = JsonResponseService.class)
public class JsonResponseServiceTest {

    @MockBean
    private UserRepository userRepositoryMock;

    @Autowired
    private JsonResponseService jsonResponseService;

    @Autowired
    private Gson gson;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void returnsJsonResponseWithToken() {
        TestEnvironment testEnv = new TestEnvironment().createBasicEnvironment();

        User testUser = testEnv.getBasicUser();

        Mockito.when(userRepositoryMock.findByUsername("basicUser")).thenReturn(testUser);

        String jsonResponse = jsonResponseService.getJsonResponseWithToken("stringObject", testUser.getUsername());
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, HashMap.class);

        assertEquals("stringObject", responseMap.get("data"));
        assertTrue(jwtUtil.validateToken((String) responseMap.get("jwt"), testUser));
    }


}

@TestConfiguration
class JsonResponseServiceTestConfiguration {

    @Bean
    public static Gson gson() {
        return new Gson();
    }

    @Bean
    public static JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    @Bean
    @Autowired
    public static UserRepositoryUserDetailService userRepositoryUserDetailService(UserRepository userRepository) {
        return new UserRepositoryUserDetailService(userRepository);
    }
}
