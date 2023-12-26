package com.nttdata.users.client;

import com.nttdata.users.dtos.LoginForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "tokenClient", url = "${oauth2.server.url}")
public interface KeycloackClient {

    @PostMapping(value = "${oauth2.token.endpoint}" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String getToken(
            @RequestBody LoginForm loginForm
    );

}
