package com.project.blog.domain.post;

import com.project.blog.domain.comment.CommentEntity;
import com.project.blog.domain.user.UserEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Table(name = "POST")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PostEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(name = "title")
    @NonNull
    private String title;

    @Column(name = "content")
    @NonNull
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post") // 연관관계의 주인 X
    private List<CommentEntity> comment;

    @CreationTimestamp //시간 자동 입력
    private Timestamp create_date;



}
