package com.project.blog.domain.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.blog.domain.comment.CommentEntity;
import com.project.blog.domain.user.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "POST")
@NoArgsConstructor
public class PostEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title")
    @Nullable
    private String title;

    @Column(name = "content")
    @Nullable
    private String content;

    @Column(name = "createDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties({"postList"})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    @JsonIgnoreProperties({"post"})
    private List<CommentEntity> comment;

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = LocalDateTime.now();
    }

    // 연관관계 설정
    public void setUser(UserEntity user) {
        this.user = user;
        if (!user.getPost().contains(this)){
            user.getPost().add(this);
        }
    }

    public UserEntity getUser() {
        return user;
    }
}
