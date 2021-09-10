package com.project.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.blog.domain.user.UserEntity;
import com.project.blog.dto.KakaoProfile;
import com.project.blog.dto.OAuthToken;
import com.project.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.UUID;

//@RequiredArgsConstructor
@Controller
public class kakaoApiController {

    @Value("${kakao.key}")
    private String cred_key;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam("code") String code){


        // POST 방식으로 key&value 데이터 요청
        RestTemplate rt = new RestTemplate();

        // HttpHeader Object 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id","2602c9fd6c651b2eb7c6ffd033249ca9");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        // Http Header & Body 하나의 object 로 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청 POST 방식 -> response 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // Json Simple Object Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        }catch(JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println(code);
        System.out.println("액세스 토큰 " + oauthToken.getAccess_token());

        // POST 방식으로 key&value 데이터 요청
        RestTemplate rt2 = new RestTemplate();
        // HttpHeader Object 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Http Header & Body 하나의 object 로 담기
        HttpEntity<HttpHeaders> kakaoProfileRequest2 = new HttpEntity<>(headers2);

//         Http 요청 POST 방식 -> response 받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        System.out.println(response2.getBody());

        // Json Simple Object Mapper
        KakaoProfile kakaoProfile = null;
        ObjectMapper objectMapper2 = new ObjectMapper();

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        }catch(IOException e){
            e.printStackTrace();
        }

        UUID tempPassword = UUID.randomUUID();

        System.out.println(tempPassword);

        UserEntity kakaoUser = UserEntity.builder()
                .username(kakaoProfile.getProperties().getNickname())
                .password(cred_key)
                .build();

        UserEntity originUser = userService.회원찾기(kakaoUser.getUsername());


        if(originUser == null) {
            userService.회원가입(kakaoUser);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cred_key));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        System.out.println("Auth Success");

        return "redirect:/";
    }
}
