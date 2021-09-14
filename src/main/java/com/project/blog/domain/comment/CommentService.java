package com.project.blog.domain.comment;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.post.PostRepository;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import com.project.blog.dto.CommentSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 댓글작성(CommentSaveDto commentSaveDto){
        System.out.println("댓글작성 진입 완료");

        UserEntity user = userRepository.findById(commentSaveDto.getUserId()).orElseThrow(() -> {
            return new IllegalArgumentException("User 없음");
        });

        PostEntity post = postRepository.findById(commentSaveDto.getPostId()).orElseThrow(() -> {
            return new IllegalArgumentException("Post 없음");
        });

        System.out.println(commentSaveDto.getText());
        CommentEntity comment = CommentEntity.builder().post(post).user(user).text(commentSaveDto.getText()).build();
        System.out.println("build success");

        commentRepository.save(comment);
    }
}
