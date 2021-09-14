package com.project.blog.controller.api;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.comment.CommentEntity;
import com.project.blog.domain.comment.CommentService;
import com.project.blog.domain.post.PostEntity;
import com.project.blog.dto.CommentSaveDto;
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

    @Autowired
    private CommentService commentService;

    @PostMapping("/write")
    public ResponseDto<Integer> save(@RequestBody PostEntity post, @AuthenticationPrincipal PrincipalDetail principal){
        postService.글쓰기(post, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/post/{postId}/reply")
    public ResponseDto<Integer> commentSave(@RequestBody CommentSaveDto commentSaveDto){

        commentService.댓글작성(commentSaveDto);

        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseDto<Integer> postDelete(@PathVariable Integer id){
        postService.삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @PutMapping("/post/update/{id}")
    public ResponseDto<Integer> postUpdate(@PathVariable Integer id ,@RequestBody PostEntity post){
        postService.수정하기(post, id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    };
}
