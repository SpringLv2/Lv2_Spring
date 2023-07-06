package com.sparta.spring_lv1.dto;

import com.sparta.spring_lv1.entity.Board;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
public class BoardResponseDto {
//    private Long id;                    // 글번호 식별자(자동)
    private String username;                // 입력한 이름
    private String title;               // 입력한 제목
    private String contents;            // 작성 내용
//    private String password;            // 입력한 비밀번호
    private LocalDateTime createdAt;         // 작성된 시간(자동)
//    private LocalDateTime modifiedAt;          // 수정된 시간(자동)

    public BoardResponseDto(Board board) {
//        this.id = board.getId();
        this.username = board.getUsername();
        this.title = board.getTitle();
        this.contents = board.getContents();
//        this.password = board.getPassword();
        this.createdAt = board.getCreatedAt();
//        this.modifiedAt = board.getModifiedAt();
    }


//    public BoardResponseDto(String username, String title, String contents, LocalDateTime createdAt) {
//        this.username = username;
//        this.title = title;
//        this.contents = contents;
//        this.createdAt = createdAt;
//    }
}
