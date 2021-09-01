package com.project.blog.service;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.post.PostRepository;
import com.project.blog.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
