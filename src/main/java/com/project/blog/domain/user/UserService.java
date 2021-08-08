package com.project.blog.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 전체 서비스가 하나의 트랜잭션으로 묶이게 된다
    // 전체가 성공을 하면 커밋이 될 것임
    @Transactional
    public int Join(UserEntity user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService : 회원가입() :"+ e.getMessage());
        }
        return -1;
    }

}
