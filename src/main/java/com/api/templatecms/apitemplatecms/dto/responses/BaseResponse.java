package com.api.templatecms.apitemplatecms.dto.responses;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> {
    public String errorCode;
    public String errorMessage;
    public T data;
    public List<String> errors;
}
