package com.api.templatecms.apitemplatecms.dto.sso.unlock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SsoUnlockResponse {
    String userid;
    String token;
    String refreshToken;
    Long expires;
}
