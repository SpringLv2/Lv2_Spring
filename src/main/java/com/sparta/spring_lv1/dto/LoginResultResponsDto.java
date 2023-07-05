package com.sparta.spring_lv1.dto;

import lombok.Getter;

@Getter
public class LoginResultResponsDto {
    private String msg;
    private String statusCode;

    public LoginResultResponsDto(String msg, String statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
