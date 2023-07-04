package com.sparta.spring_lv1.service;

import com.sparta.spring_lv1.dto.LoginRequestDto_bata;
import com.sparta.spring_lv1.dto.SignupRequestDto_bata;
import com.sparta.spring_lv1.entity.Board;
import com.sparta.spring_lv1.entity.UserRoleEnum;
import com.sparta.spring_lv1.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService_bata {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService_bata(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // ADMIN_TOKEN : 일반사용자인지 관리자인지 구분하기 위해서 만든것, Token을 통해서 관리자여부를 구분한다(실제론 이렇게 부여하지 않음)
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto_bata requestDto) {  // 회원가입할 데이터를 requestDto로 받아온다
        String username = requestDto.getUsername();     // username 받아옴
        String password = passwordEncoder.encode(requestDto.getPassword()); // password를 가져와서 edcode진행함

        // 회원 중복 확인
        Optional<Board> checkUsername = userRepository.findByUsername(username);  // 중복여부 확인하기
        if (checkUsername.isPresent()) {    // .isPresent()  Optional<User> checkUsername에 넣어준 값이 존재하는지 안하는지를 확인하는 메서드
                                            // True인 경우 DB에 username이 있다는거라서 "중복"이다. -> IllegalArgumentException 실행
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<Board> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;  // 일단 일반사용자 권한으로 넣어둠
        if (requestDto.isAdmin()) {  // true면 관리자를 확인하는 코드가 진행된다, false인 경우 일반사용자로 가입함
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        Board user = new Board(username, password, email, role);  // 생성자 추가생성
        userRepository.save(user);
    }

    public void login(LoginRequestDto_bata requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인
        Board board = userRepository.findByUsername(username).orElseThrow(  // 회원가입이랑 반대로 없을때가 오류임
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, board.getPassword())) { // matches(확인할 데이터값, 기존 값(엔티티_)
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 인증되었음. JWT 생성 및 쿠키에 저장 후 Response 객체에 추가하기
    }
}