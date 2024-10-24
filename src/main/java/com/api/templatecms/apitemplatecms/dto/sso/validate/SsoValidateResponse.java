package com.api.templatecms.apitemplatecms.dto.sso.validate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SsoValidateResponse {
    String nip;
    String role;
}
