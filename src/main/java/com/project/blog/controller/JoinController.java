package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JoinController {

    @GetMapping( "/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping( "/auth/loginForm")
    public String signinForm(){

        return "user/loginForm";
    }
}
