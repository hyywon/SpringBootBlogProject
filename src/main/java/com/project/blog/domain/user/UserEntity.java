package com.project.blog.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.blog.domain.post.PostEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    String password;
    public void setName(String name) {
        this.name = name;
    }


    @Builder
    public UserEntity(String name, String password){
        this.name = name;
        this.password = password;
    }
}
