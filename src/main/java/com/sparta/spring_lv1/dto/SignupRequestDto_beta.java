package com.sparta.spring_lv1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto_beta {
    private String username;
    private String password;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}