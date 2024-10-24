package com.api.templatecms.apitemplatecms.dto.sso;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SsoBaseResponse<T> {
    String errorCode;
    String errorMessage;
    T data;
}
