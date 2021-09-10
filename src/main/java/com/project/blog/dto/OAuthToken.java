package com.project.blog.dto;

import lombok.Data;
@Data
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;

    public String getAccess_token(){
        return access_token;
    }

    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }

    public String getToken_type(){
        return token_type;
    }

    public void setToken_type(String token_type){
        this.token_type = token_type;
    }
}
