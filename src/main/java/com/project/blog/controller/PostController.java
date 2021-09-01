package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.post.PostEntity;
import com.project.blog.dto.ResponseDto;
import com.project.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping({"","/"})
    public String index(){
        return "index";
    }

    // USER 권한 필요
    @GetMapping("/post/saveForm")
    public String saveForm(){
        return "post/saveForm";
    }

    @PostMapping("/write")
    public ResponseDto<Integer> save(@RequestBody PostEntity post, @AuthenticationPrincipal PrincipalDetail principal){
        postService.글쓰기(post, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
