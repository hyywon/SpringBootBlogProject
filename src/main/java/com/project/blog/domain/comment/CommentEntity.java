package com.project.blog.domain.comment;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.user.UserEntity;
import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "COMMENT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    @NonNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
