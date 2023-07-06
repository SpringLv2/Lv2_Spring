package com.sparta.spring_lv1.controller;

import com.sparta.spring_lv1.dto.LoginRequestDto;
import com.sparta.spring_lv1.dto.ResultResponsDto;
import com.sparta.spring_lv1.dto.SignupRequestDto;
import com.sparta.spring_lv1.entity.User;
import com.sparta.spring_lv1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
  
    @PostMapping("/signup")
    public ResultResponsDto signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult, HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getAttribute("user");

        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new ResultResponsDto("회원가입에 실패하셨습니다.", "400");

        }
        userService.signup(requestDto);

        return new ResultResponsDto("회원가입 성공","200");
    }



//    @GetMapping("/login-page")   // 로그인
//    public String loginPage() {
//        return "login";
//    }
//
//    @GetMapping("/signup")   // 회원가입
//    public String signupPage() {
//        return "signup";
//    }

    @PostMapping("/login")
    public ResultResponsDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {

        try { 
            userService.login(requestDto, res);

        } catch (Exception e) {
            return new ResultResponsDto("로그인 에러","404");
        }
        return new ResultResponsDto("로그인 성공","200");
    }
}
