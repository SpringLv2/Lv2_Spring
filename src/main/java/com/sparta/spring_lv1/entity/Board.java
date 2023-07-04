package com.sparta.spring_lv1.entity;

import com.sparta.spring_lv1.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "board")   // JPA 매핑할 테이블 이름
@NoArgsConstructor
public class Board extends  Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 글번호 식별자(자동)

    @Column(name = "username", unique = true)
    private String username;                // 사용자 이름

    @Column(name = "title", nullable = false, length = 100)
    private String title;               // 입력한 제목

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;            // 작성 내용

    @Column(name = "password", nullable = false)
    private String password;            // 입력한 비밀번호


    @Column(nullable = false, unique = true)
    private String email;


    // 사용자 권한, 저장은 권한명 그대로 진행함
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;  // 유저용 권한

    public Board(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();

    }

    // 수정은 제목과 작성내용만 진행함
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    //
    public Board(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

}

