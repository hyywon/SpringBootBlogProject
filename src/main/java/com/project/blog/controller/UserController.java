package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.user.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

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
