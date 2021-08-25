package com.project.blog.service;

import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import com.project.blog.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public int 회원가입(UserEntity user) {

        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User Service 회원가입 " + e.getMessage());
        }
        return -1;
    }

    @Transactional(readOnly = true)
    //readonly = true : select할 떄, transaction 시작, 서비스 종료시에 transaction 종료 (정합성, 같은 데이터 select 할 수 있도록)
    public UserEntity 로그인(UserEntity user) {
        return userRepository.findByNameAndPassword(user.getName(), user.getPassword());


    }
}