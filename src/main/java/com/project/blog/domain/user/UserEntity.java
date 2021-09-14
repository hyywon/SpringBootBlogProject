package com.project.blog.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username", length = 100)
    String username;

    @Column(name = "password")
    String password;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public UserEntity(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void update(String username, String password){
        this.username = username;
        this.password = password;
    }
}
