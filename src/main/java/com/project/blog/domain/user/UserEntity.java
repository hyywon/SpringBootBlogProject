package com.project.blog.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.blog.domain.post.PostEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@NoArgsConstructor
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    String password;

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<PostEntity> postList;

    public void addPost(PostEntity post){
        this.postList.add(post);

        if (post.getUser() != this){
            post.setUser(this);
        }
    }

    public List<PostEntity> getPost() {
        return postList;
    }
    @Builder
    public UserEntity(String name, String password){
        this.name = name;
        this.password = password;

    }
}
