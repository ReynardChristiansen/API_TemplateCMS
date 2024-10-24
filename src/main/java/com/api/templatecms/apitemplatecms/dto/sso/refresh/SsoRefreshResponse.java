package com.api.templatecms.apitemplatecms.dto.sso.refresh;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SsoRefreshResponse {
    String token;
    String refreshToken;
    Long expires;
}
