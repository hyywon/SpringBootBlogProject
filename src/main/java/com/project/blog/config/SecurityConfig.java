package com.project.blog.config;

import com.project.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Bean : Spring Container 에서 객체를 관리할 수 있게 하는
// Request 요청을 가로채고 Controller 로 들어가서 동작을 하기전에 인증 설정한 주소가 아니면 인증을 해야하는 필터링을 걸치게 함
@Configuration // IoC 관리
@EnableWebSecurity // Security Filter -> Security Filter 활성화에 대한 설정을 해당 파일로 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean // Ioc 등록
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // Security 가 대신 로그인 할 때, password 가 가로채기를 하는데 해당 password 가 뭘로 해쉬가 되어서 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교가 가능
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    } // object 필요

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        // 인증되지 않은 사용자는
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/"); // 성공 했을 때 돌아갈 url
    }
}
