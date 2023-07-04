package com.sparta.spring_lv1.controller;

import com.sparta.spring_lv1.dto.LoginRequestDto_bata;
import com.sparta.spring_lv1.dto.SignupRequestDto_bata;
import com.sparta.spring_lv1.service.UserService_bata;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService_bata userService;

    public UserController(UserService_bata userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")   // 로그인
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")   // 회원가입
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")  // 회원가입 성공하면 로그인하라고 반환페이지를 줄거라서 String으로 선택
    public String signupe(SignupRequestDto_bata requestDto) { //@ModelAttribute 생략함
        userService.signup(requestDto);  // 서비스로 전달함
        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto_bata requestDto, HttpServletResponse res) {  //@ModelAttribute 생략함
        // login(LoginRequestDto requestDto, HttpServletResponse res)
        // HttpServletResponse 받아와야함
        // JWT Token을 만들고 Cookie를 생성해서 Response 객체에 넣어줘야 함
        // Servlet이 만들어 준 HttpServletResponse도 받아오도록 설정하기

        // userService.login(requestDto, res)
        // ㄴ> 로그인할때 검증하라고 받아온 데이터requestDto를 보냄
        // ㄴ> 검증완료시 JWTTokenCookie에 넣고 그 Cookie도 담으라고 받아온 requestDto도 res로 보내줌

        try { // 로그인 오류시 예외처리
            userService.login(requestDto, res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error"; // ?error : 클라이언트에서 오류시 이렇게 보내주길 요청했음
        }


        return "redirect:/";  // 성공시 메인으로 이동
    }
}