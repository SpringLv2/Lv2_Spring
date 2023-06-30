package com.sparta.spring_lv1.entity;

import com.sparta.spring_lv1.dto.BoardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Board {
    private Long id;                    // 글번호 식별자(자동)
    private String name;                // 입력한 이름
    private String title;               // 입력한 제목
    private String contents;            // 작성 내용
    private String password;            // 입력한 비밀번호
    private LocalDateTime created_at;           // 작성된 시간(자동)
    private LocalDateTime updated_at;            // 수정된 시간(자동)

    public Board(BoardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }
}

