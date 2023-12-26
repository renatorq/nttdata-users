package com.nttdata.users.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.users.client.KeycloackClient;
import com.nttdata.users.dtos.LoginForm;
import com.nttdata.users.dtos.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private KeycloackClient keycloackClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${oauth2.client.id}")
    private String clientId;

    @Value("${oauth2.client.secret}")
    private String clientSecret;

    @Value("${oauth2.client.grant_type}")
    private String grantType;

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestParam("user") String user, @RequestParam("password") String password) throws JsonProcessingException {

        String response = keycloackClient.getToken(LoginForm.builder()
                        .client_id(clientId)
                        .client_secret(clientSecret)
                        .grant_type(grantType)
                        .password(password)
                        .username(user)
                .build());

        return objectMapper.readValue(response, TokenResponseDTO.class);
    }
}
