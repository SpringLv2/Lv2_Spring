package com.sparta.spring_lv1.controller;

import com.sparta.spring_lv1.dto.SignupRequestDto;
import com.sparta.spring_lv1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //
//    // 회원가입
//    @GetMapping("/user/sigup")
//    public String sigupPage(){
//        return "signup";
//    }

    @PostMapping("/api/auth/signup")
    public String signup(SignupRequestDto requestDto){
        userService.signup(requestDto);

        return "";
    }
}
