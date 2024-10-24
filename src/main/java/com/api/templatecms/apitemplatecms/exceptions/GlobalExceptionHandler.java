package com.api.templatecms.apitemplatecms.exceptions;

import com.api.templatecms.apitemplatecms.constants.ErrorCodeConstants;
import com.api.templatecms.apitemplatecms.constants.ErrorMessageConstants;
import com.api.templatecms.apitemplatecms.dto.responses.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleException(Exception ex) {
        BaseResponse<Object> errorResponse = new BaseResponse<>();
        errorResponse.setErrorCode(ErrorCodeConstants.GENERAL_ERROR);
        errorResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<BaseResponse<Object>> handleUnauthorizedException(UnauthorizedException ex) {
        BaseResponse<Object> errorResponse = new BaseResponse<>();
        errorResponse.setErrorCode(ErrorCodeConstants.UNAUTHORIZED);
        errorResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        BaseResponse<Object> rsp = new BaseResponse<>();
        rsp.setErrorCode(ErrorCodeConstants.FORBIDDEN_REQ);
        rsp.setErrorMessage(ErrorMessageConstants.FORBIDDEN_REQ);

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        rsp.setErrors(errors);
        rsp.setData(null);
        return new ResponseEntity<>(rsp, HttpStatus.BAD_REQUEST);
    }
}
