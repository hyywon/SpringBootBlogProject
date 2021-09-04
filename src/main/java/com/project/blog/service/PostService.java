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

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Page<PostEntity> 게시글목록(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PostEntity 상세보기(Integer id) {
        // findById -> return entity
        return postRepository.findById(id).orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패");
                });
    }

    @Transactional
    public void 삭제하기(Integer id){
        postRepository.deleteById(id);
    }

    @Transactional
    public void 수정하기(PostEntity post, Integer id){
        PostEntity selectPost = postRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 업데이트 실패");
        });
        selectPost.setTitle(post.getTitle());
        selectPost.setContent(post.getContent());
    }
}
