package com.project.blog.domain.comment;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.user.UserEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
