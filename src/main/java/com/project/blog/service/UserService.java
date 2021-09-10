package com.project.blog.service;

import com.project.blog.domain.post.PostEntity;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import com.project.blog.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service // Spring Component Scan을 통해서, Bean 에 등록 -> IoC 해줌
public class UserService {

    // 회원가입 전체 서비스가 하나의 트랜잭션으로 묶이게 된다
    // 전체가 성공을 하면 커밋이 될 것임
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public UserEntity 회원찾기(String username){
        System.out.println("회원 찾기");

        UserEntity user = userRepository.findByUsername(username).orElseGet(()->{
            return null;
        });
        System.out.println("회원 찾기 success");

        return user;
    }

    @Transactional
    public void 회원가입(UserEntity user) {

        String rawPassword = user.getPassword(); // 원본 비밀번호
        String encPassword = encoder.encode(rawPassword); // 해쉬 비밀번호

        user.setPassword(encPassword);

        userRepository.save(user);
        System.out.println("회원가입 success");
    }

    @Transactional
    public void 정보수정(UserEntity user){
        UserEntity selectUser = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원정보 업데이트 실패");
        });
        System.out.println(user.getUsername() + user.getPassword() + "update");
        String rawPassword = user.getPassword(); // 원본 비밀번호
        String encPassword = encoder.encode(rawPassword); // 해쉬 비밀번호

        selectUser.update(user.getUsername(),encPassword);
    }
}