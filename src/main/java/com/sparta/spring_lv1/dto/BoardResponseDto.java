package com.sparta.spring_lv1.dto;

import com.sparta.spring_lv1.entity.Board;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
public class BoardResponseDto {
    private Long id;                    // 글번호 식별자(자동)
    private String name;                // 입력한 이름
    private String title;               // 입력한 제목
    private String contents;            // 작성 내용
    private String password;            // 입력한 비밀번호
    private LocalDateTime created_at;         // 작성된 시간(자동)
    private LocalDateTime updated_at;          // 수정된 시간(자동)

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.name = board.getName();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.password = board.getPassword();
        this.created_at = board.getCreated_at();
        this.updated_at = board.getUpdated_at();
    }

    public BoardResponseDto(Long id, String name, String title, String contents, String password, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

//    public BoardResponseDto(Long id, String name, String title, String contents, LocalDateTime updated_at) {
//        this.id = id;
//        this.name = name;
//        this.title = title;
//        this.contents = contents;
//        this.updated_at = updated_at;
//    }
}
