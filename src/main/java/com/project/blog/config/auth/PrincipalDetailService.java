package com.project.blog.config.auth;

import com.project.blog.domain.user.UserEntity;
import com.project.blog.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Bean
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Spring 로그인 요청을 가로챌 때, username, password 변수를 가로채는데 password 부분처리는 알아서함
    // username 이 DB에 있는지 확인 하는 함수
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity principal = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : " + username);
                });
        return new PrincipalDetail(principal); // Security Session 에 유저 정보가 저장됨
    }
}
