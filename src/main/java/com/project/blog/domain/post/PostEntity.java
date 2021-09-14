package com.project.blog.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    // cascadetype.remove : board 데이터를 삭제할 때 comment 도 함께 삭제
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = CascadeType.REMOVE) // 연관관계의 주인 X
    @OrderBy("id desc")
    @JsonIgnoreProperties({"post","user"})
    // json parsing 하면 comment 에서 무한참조가 되므로 postEntity 에서 JsonIgnore 를 사용해줌
    // PostEntity 서만 무한참조 안하도록 설정
    private List<CommentEntity> comment;

    @CreationTimestamp //시간 자동 입력
    private Timestamp create_date;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
