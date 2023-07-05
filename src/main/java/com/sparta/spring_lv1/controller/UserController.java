package com.sparta.spring_lv1.controller;

import com.sparta.spring_lv1.dto.LoginResultResponsDto;
import com.sparta.spring_lv1.dto.SignupRequestDto;
import com.sparta.spring_lv1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/auth/signup")
    @ResponseBody
    public LoginResultResponsDto signup(@RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);

        return new LoginResultResponsDto("로그인 성공","200");
    }
}
