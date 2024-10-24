package com.api.templatecms.apitemplatecms.dto.requests.ssologin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BodyReqSsoLoginDto {
    @NotNull(message="bcafCode Cannot Be Null")
    @NotEmpty(message="bcafCode Cannot Be Empty")
    @NotBlank(message="bcafCode Cannot Be Blank")
    String bcafCode;
}
