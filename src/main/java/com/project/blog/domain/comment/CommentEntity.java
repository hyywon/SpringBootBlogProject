package com.project.blog.domain.comment;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.user.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "post_id")
    private Integer post_id;

    @Column(name = "writer")
    private String writer;

    public void setText(String text) {
        this.text = text;
    }

    @Builder
    public CommentEntity(String writer, Integer post_id, String text){
        this.writer = writer;
        this.post_id = post_id;
        this.text = text;
    }
}
