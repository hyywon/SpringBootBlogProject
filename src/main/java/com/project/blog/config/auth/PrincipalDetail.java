package com.project.blog.config.auth;

import com.project.blog.domain.user.UserEntity;
import lombok.Getter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Spring Security 가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 User Details 타입의 오브젝트를
// Spring Security 의 고유한 세션 저장소에 저장 -> PrincipalDetail
// Overriding 필요

@Getter
public class PrincipalDetail implements UserDetails {

    private UserEntity user; // Composition

    public PrincipalDetail(UserEntity user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되었는지 확인 (ture: 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않는지 확인 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었는지 확인 (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 확인 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정의 권한 목록을 반환 (권한이 여러개 있을 수 있어서 루프를 돌아야 하지만, 여기서는 한개만 가져옴)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // GrantedAuthority를 상속받은 Collection 타입
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(() -> {
            return "ROLE_USER";
        });
        return collectors;
    }
}
