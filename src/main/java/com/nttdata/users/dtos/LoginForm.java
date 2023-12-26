package com.nttdata.users.dtos;

import lombok.Builder;

@Builder
public class LoginForm {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
}
