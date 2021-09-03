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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/dummy/paging")
    public Page<PostEntity> pages(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostEntity> posts = postRepository.findAll(pageable);

        List<PostEntity> postlist = posts.getContent();

        return posts;
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
