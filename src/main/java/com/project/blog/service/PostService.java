package com.project.blog.service;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.post.PostRepository;
import com.project.blog.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void 글쓰기(PostEntity post, UserEntity user){
        // title, content, user
        post.setUser(user);
        postRepository.save(post);
    }

    @Transactional
    public Page<PostEntity> 게시글목록(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

}
