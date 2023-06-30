package com.sparta.spring_lv1.repository;

import com.sparta.spring_lv1.dto.BoardRequestDto;
import com.sparta.spring_lv1.dto.BoardResponseDto;
import com.sparta.spring_lv1.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;


@Component
@RequiredArgsConstructor
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate; // @RequiredArgsConstructor

    // 작성
    public Board save(Board board) {
        // DB 저장

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        String sql = "INSERT INTO board (name, title, contents, password) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, board.getName());
                    preparedStatement.setString(2, board.getTitle());
                    preparedStatement.setString(3, board.getContents());
                    preparedStatement.setString(4, board.getPassword());
                    return preparedStatement;
                },
                keyHolder);
        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        board.setId(id);

        return board;
    }


    // 전체조회
    public List<BoardResponseDto> findAll() {
        String sql = "SELECT * FROM board ORDER BY created_at DESC";  // 내림차순 정렬
        return jdbcTemplate.query(sql, new RowMapper<BoardResponseDto>() {

            @Override
            public BoardResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String password = rs.getString("password");
                LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime updated_at = rs.getTimestamp("updated_at").toLocalDateTime();

                return new BoardResponseDto(id, name, title, contents, password, created_at, updated_at);
//              return new BoardResponseDto(id, name, title, contents,updated_at);
            }
        });
    }


    // 선택조회
    public BoardResponseDto findOne(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        Board board = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setId(rs.getLong("id"));
                board.setName(rs.getString("name"));
                board.setTitle(rs.getString("title"));
                board.setContents(rs.getString("contents"));
                board.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                board.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                return board;
            }
        });

        if (board != null) {
            return new BoardResponseDto(board);
        } else {
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }

    // 수정
    public void update(Long id, BoardRequestDto requestDto) {
        // 게시글 내용 수정
        // ★ 수정범위 : 제목과 내용만 가능
        String sql = "UPDATE board SET name = ?, title = ?, contents = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getName(), requestDto.getTitle(), requestDto.getContents()
                , new Timestamp(System.currentTimeMillis()), id);
    }

    // 삭제
    public void delete(Long id, BoardRequestDto requestDto) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    // findById() 설정
    public Board findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM board WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Board board = new Board();
                board.setName(resultSet.getString("name"));
                board.setTitle(resultSet.getString("title"));
                board.setContents(resultSet.getString("contents"));
                board.setPassword(resultSet.getString("password"));
                return board;
            } else {
                return null;
            }
        }, id);
    }

}
