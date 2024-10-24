package com.api.templatecms.apitemplatecms.dto.requests.ott;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OttRequest {
    @NotNull(message = "One Time Token Cannot Be Null")
    @NotEmpty(message = "One Time Token Cannot Be Empty")
    @NotBlank(message = "One Time Token Cannot Be Blank")
    String ott;
    @NotNull(message = "apiSign Cannot Be Null")
    @NotEmpty(message = "apiSign Cannot Be Empty")
    @NotBlank(message = "apiSign Cannot Be Blank")
    String apiSign;
}
