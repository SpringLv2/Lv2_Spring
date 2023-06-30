package com.sparta.spring_lv1.service;


import com.sparta.spring_lv1.dto.BoardRequestDto;
import com.sparta.spring_lv1.dto.BoardResponseDto;
import com.sparta.spring_lv1.entity.Board;
import com.sparta.spring_lv1.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 데이터베이스 -> @RequiredArgsConstructor 로 생성



    // 생성
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);  // 상황에 맞는 생성자 지정함

        // DB저장
        Board saveBoard = boardRepository.save(board);

        // Entity -> ResponseDto로 변환을 진행함
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);  // BoardResponseDto에 board 생성자 추가등록

        return boardResponseDto;
    }


    // 전체조회
    public List<BoardResponseDto> getBoard() {
        // DB 조회
        return boardRepository.findAll();


    }


    // 선택조회
    public BoardResponseDto getBoardById(Long id) {
        // 해당 ID의 게시글을 조회

        return boardRepository.findOne(id);
    }


    // 수정
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        // 해당 게시글이 DB에 존재하는지 확인

        Board board = boardRepository.findById(id);
        if (board != null) {
            // 비밀번호 일치 여부 확인
            if (requestDto.getPassword().equals(board.getPassword())) {

                boardRepository.update(id, requestDto);

                return id;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }

    // 삭제
    public Long deleteMemo(Long id, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = boardRepository.findById(id);
        // 비밀번호 검증 필요함
        if (board != null) {
            if (requestDto.getPassword().equals(board.getPassword())) {
                // 게시글 삭제
                boardRepository.delete(id, requestDto);
                System.out.println("삭제가 완료되었습니다.");

                return id;
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }
}
