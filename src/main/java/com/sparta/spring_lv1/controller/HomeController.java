package com.sparta.spring_lv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {  // 메인페이지에 가기 위해서 만든 컨트롤러

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username", "username");
        return "index";
    }

    @GetMapping("/board/write")
    public String write(){
        return "post";
    }
}