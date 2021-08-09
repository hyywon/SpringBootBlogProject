package com.project.blog.controller;

import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // http://localhost:8000/user/detail/{id} (요청)
    @GetMapping("/user/detail/{id}")
    public UserEntity detail(@PathVariable Integer id){
        // findById -> return entity
        Optional<UserEntity> user = userRepository.findById(id);

        // Optional 값이기 때문에 ifPresent 를 통해서 먼저 확인
        user.ifPresent(selectUser ->{
                System.out.println("username" + selectUser.getName());
                System.out.println("password" + selectUser.getPassword());
            }
        );
        return user.get();
    }

    // http://localhost:8000/user/detail (요청)
    @GetMapping("/user/detail")
    public List<UserEntity> allSearch(){

        List<UserEntity> userList = userRepository.findAll();

        return userList;
    }

    @Transactional
    @PutMapping("/user/update/{id}")
    public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserEntity requestUser){
        // update 할 때는 save() 사용 안함 => 더티 체킹
        UserEntity user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });

        user.setName(requestUser.getName());
        user.setPassword(requestUser.getPassword());

        return null;
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}
