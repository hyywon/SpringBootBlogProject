package com.project.blog.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.blog.domain.comment.CommentEntity;
import com.project.blog.domain.user.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "POST")
@NoArgsConstructor
public class PostEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(name = "title")
    @Nullable
    private String title;

    @Column(name = "content")
    @Nullable
    private String content;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "user_id")

    @Column(name = "writer")
    private String writer;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//    @JsonIgnoreProperties({"post"})
//    private List<CommentEntity> comment;

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Builder
    public PostEntity(String writer,String title, String content){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    //연관관계 설정
//    public void setUser(UserEntity user) {
//        this.user = user;
//        if (!user.getPost().contains(this)){
//            user.getPost().add(this);
//        }
//    }
}
