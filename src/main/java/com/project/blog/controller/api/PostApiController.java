package com.project.blog.controller.api;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.post.PostEntity;
import com.project.blog.dto.ResponseDto;
import com.project.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
public class PostApiController {

    @Autowired
    private PostService postService;


    @PostMapping("/write")
    public ResponseDto<Integer> save(@RequestBody PostEntity post, @AuthenticationPrincipal PrincipalDetail principal){
        postService.글쓰기(post, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseDto<Integer> postDelete(@PathVariable String id){
        int ids = Integer.parseInt(id);
        postService.삭제하기(ids);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }
}
