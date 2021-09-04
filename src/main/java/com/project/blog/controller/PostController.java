package com.project.blog.controller;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.post.PostRepository;
import com.project.blog.dto.ResponseDto;
import com.project.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostEntity> posts = postService.게시글목록(pageable);
        model.addAttribute("posts", posts);
        return "index";
    }


    // USER 권한 필요
    @GetMapping("/post/saveForm")
    public String saveForm(){
        return "post/saveForm";
    }


    @GetMapping("/post/{id}")
    public String postDetail(Model model, Principal principal, @PathVariable Integer id){
        model.addAttribute("post", postService.상세보기(id));
        model.addAttribute("principal", principal);
        return "post/postDetail";
    }

    @GetMapping("/post/{id}/update")
    public String postUpdate(@PathVariable Integer id, Model model){
        model.addAttribute("post", postService.상세보기(id));
        return "post/updateForm";
    }
}
