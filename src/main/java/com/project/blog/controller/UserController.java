package com.project.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Controller
public class UserController {

    @GetMapping( "/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping( "/auth/loginForm")
    public String signinForm(){

        return "user/loginForm";
    }

    @GetMapping("user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }
}
