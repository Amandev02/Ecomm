package com.ecommerce.sb_ecom.security.response;

import lombok.Data;

import java.util.List;


@Data
public class LoginResponse {

    private Long id;
    private String jwtToken;
    private String username;
    private List<String> roles;

    public LoginResponse(Long id, String username, List<String> roles, String jwtToken) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.jwtToken = jwtToken;
    }

    public LoginResponse(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}