package com.project.blog.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class KakaoProfile{
    public int id;
    public String connected_at;
    public Properties properties;
    public KakaoAccount kakao_account;

    @Data
    public class Properties {
        public String nickname;
    }

    @Data
    @NoArgsConstructor
    public class KakaoAccount {
        public Boolean profile_nickname_needs_agreement;
        public Profile profile;

        @JsonCreator
        public KakaoAccount(Boolean profile_nickname_needs_agreement, Profile profile){
            this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
            this.profile = profile;
        }

        @Data
        @NoArgsConstructor
        public class Profile{
            public String nickname;

            @JsonCreator
            public Profile(String nickname){
                this.nickname = nickname;
            }
        }
    }


}
