package com.project.blog.controller;

import com.project.blog.domain.user.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JoinController {

    @GetMapping( "/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping( "/signinForm")
    public String signinForm(){

        return "user/signinForm";
    }

}
