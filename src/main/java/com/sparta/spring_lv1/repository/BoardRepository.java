package com.sparta.spring_lv1.repository;

import com.sparta.spring_lv1.dto.BoardRequestDto;
import com.sparta.spring_lv1.dto.BoardResponseDto;
import com.sparta.spring_lv1.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    // 전체조회
    List<Board> findAllByOrderByModifiedAtDesc();  // Service의 전체조회 findAll()과 대체함


}
