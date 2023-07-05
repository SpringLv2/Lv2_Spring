package com.sparta.spring_lv1.controller;

import com.sparta.spring_lv1.dto.LoginRequestDto;
import com.sparta.spring_lv1.dto.ResultResponsDto;
import com.sparta.spring_lv1.dto.SignupRequestDto;
import com.sparta.spring_lv1.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    @ResponseBody
    public ResultResponsDto signup(@RequestBody @Valid SignupRequestDto requestDto){
        userService.signup(requestDto);

        return new ResultResponsDto("회원가입 성공","200");
    }

//    @PostMapping("/signup")  // 회원가입 성공하면 로그인하라고 반환페이지를 줄거라서 String으로 선택
//    public String signupe(SignupRequestDto requestDto) { //@ModelAttribute 생략함
//        userService.signup(requestDto);  // 서비스로 전달함
//        return "redirect:/api/user/login-page";
//    }

    @GetMapping("/login-page")   // 로그인
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")   // 회원가입
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultResponsDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {


        try { // 로그인 오류시 예외처리
            userService.login(requestDto, res);

        } catch (Exception e) {
            return new ResultResponsDto("로그인 에러","404");// ?error : 클라이언트에서 오류시 이렇게 보내주길 요청했음
        }
        return new ResultResponsDto("로그인 성공","200");
//        return new ResultResponsDto("로그인 성공","200");  // 성공시 메인으로 이동
    }
}