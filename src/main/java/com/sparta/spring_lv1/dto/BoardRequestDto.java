package com.sparta.spring_lv1.dto;

import com.sparta.spring_lv1.entity.Board;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;               // 입력한 제목
    private String contents;            // 작성 내용
}
