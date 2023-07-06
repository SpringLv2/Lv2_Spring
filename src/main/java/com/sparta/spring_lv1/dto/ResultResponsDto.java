package com.sparta.spring_lv1.dto;

import lombok.Getter;

@Getter
public class ResultResponsDto {
    private String msg;
    private String statusCode;

    public ResultResponsDto(String msg, String statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}