package com.project.blog.controller.api;

import com.project.blog.config.auth.PrincipalDetail;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import com.project.blog.dto.ResponseDto;
import com.project.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class UserApiController {

    @Autowired // DI
    public UserRepository userRepository;

    @Autowired // DI
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> join(@RequestBody UserEntity user){ //name, password
        System.out.println(user.getUsername() + user.getPassword());
        System.out.println("User API 호출");
        userService.회원가입(user);
        System.out.println(user.getPassword());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // http://localhost:8000/user/detail/{id} (요청)
    @GetMapping("/user/detail/{id}")
    public UserEntity detail(@PathVariable Integer id) {
        // findById -> return entity
        Optional<UserEntity> user = userRepository.findById(id);

        // Optional 값이기 때문에 ifPresent 를 통해서 먼저 확인
        user.ifPresent(selectUser -> {
                    System.out.println("username" + selectUser.getUsername());
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
    @PutMapping("/user/update")
    public ResponseDto<Integer> updateUser(@RequestBody UserEntity user){
        userService.정보수정(user);

        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}
