package com.project.blog.controller;

import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import com.project.blog.dto.ResponseDto;
import com.project.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired // DI
    public UserRepository userRepository;

    @Autowired // DI
    private UserService userService;

    @Autowired
    private HttpSession session;

    // http://localhost:8000/user/join (요청)
    // BODY 에 name, password 입력
//    @PostMapping("/user/join")
//    public String join(UserEntity user){
//        System.out.println("USER name " + user.getName());
//        System.out.println("USER password " + user.getPassword());
//
//        userRepository.save(user);
//
//        return "Join Success";
//    }

    @PostMapping("/user/join")
    public ResponseDto<Integer> join(@RequestBody UserEntity user){ //name, password
        System.out.println(user.getName() + user.getPassword());
        System.out.println("User API 호출");
        int result = userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
    }

    @PostMapping("/user/signin")
    public ResponseDto<Integer> signin(@RequestBody UserEntity user){
        System.out.println(user.getName() + user.getPassword());
        System.out.println("User SignIn API 호출");
        UserEntity principal = userService.로그인(user); // 접근 주체 객체 생성

        if (principal != null){
            session.setAttribute("principal", principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // http://localhost:8000/user/detail/{id} (요청)
    @GetMapping("/user/detail/{id}")
    public UserEntity detail(@PathVariable Integer id) {
        // findById -> return entity
        Optional<UserEntity> user = userRepository.findById(id);

        // Optional 값이기 때문에 ifPresent 를 통해서 먼저 확인
        user.ifPresent(selectUser -> {
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
