package com.project.blog.controller.api;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.post.PostRepository;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.dto.ResponseDto;
import com.project.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostApiController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;


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

//    @GetMapping("/dummy/paging")
//    public Page<PostEntity> pages(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<PostEntity> posts = postRepository.findAll(pageable);
//
//        List<PostEntity> postlist = posts.getContent();
//
//        return posts;
//    }