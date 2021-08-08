package com.project.blog.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired // DI
    public UserRepository userRepository;

    // http://localhost:8000/user/join (요청)
    // BODY 에 name, password 입력
    @PostMapping("/user/join")
    public String join(UserEntity user){
        System.out.println("USER name " + user.getName());
        System.out.println("USER password " + user.getPassword());

        userRepository.save(user);

        return "Join Success";
    }


    @GetMapping("/user/detail/{id}")
    public UserEntity detail(@PathVariable Integer id){
        // return entity
        Optional<UserEntity> user = userRepository.findById(id);

        // Optional 값이기 때문에 ifPresent 를 통해서 먼저 확인
        user.ifPresent(selectUser ->{
                System.out.println("username" + selectUser.getName());
                System.out.println("password" + selectUser.getPassword());
            }
        );

        return user.get();
    }
}
