package com.sparta.spring_lv1.controller;


import com.sparta.spring_lv1.dto.BoardRequestDto;
import com.sparta.spring_lv1.dto.BoardResponseDto;
import com.sparta.spring_lv1.dto.ResultResponsDto;
import com.sparta.spring_lv1.entity.Board;
import com.sparta.spring_lv1.entity.User;
import com.sparta.spring_lv1.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService; //

    // 게시글 생성하기 - 토큰 검증 필요함
    @PostMapping("")
    public BoardResponseDto creatBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return boardService.createBoard(requestDto, user);//
    }

    // 게시글 전체조회
    @GetMapping("")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();  //
    }

    // 게시글 선택조회
    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    // 게시글 수정하기 - 토큰 검증, 작성자 필터링 필요함
    @PutMapping("/{id}")

    public BoardResponseDto updateBoard(@PathVariable Long id,
                                        @RequestBody BoardRequestDto requestDto,
                                        HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return boardService.updateBoard(id, requestDto, user);  //
    }

    // 메모 삭제하기 - 토큰 검증, 작성자 필터링 필요함
    @DeleteMapping("/{id}")
    public ResultResponsDto deleteMemo(@PathVariable Long id,
                                       HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        try{
            boardService.deleteMemo(id, user);
        }catch (Exception e){
            return new ResultResponsDto("게시글 작성자만 삭제할수 있습니다", "202");
        }
        return new ResultResponsDto("게시글 작성 성공","200");  //
    }
}
