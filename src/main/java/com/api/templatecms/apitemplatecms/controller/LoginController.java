package com.api.templatecms.apitemplatecms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.templatecms.apitemplatecms.dto.requests.ssologin.BodyReqSsoLoginDto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;



@RestController
@Slf4j
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private Validator validator;
    @Value("${angular.page.unauthorized}")
    String redirectUrlUnauthorized;

    @PostMapping(path="/login")
    public ResponseEntity<Map<String, Object>> openWebview(@RequestParam("bcaf-code") String bcafCode, @RequestHeader Map<String,String> meta) throws Exception {
        // Map<String, Object> response = new HashMap<>();

        // response.put("message", bcafCode);
        // return ResponseEntity.ok(response);
        String uuid = UUID.randomUUID().toString();
        BodyReqSsoLoginDto bodyReqSsoLoginDto = new BodyReqSsoLoginDto();
        bodyReqSsoLoginDto.setBcafCode(bcafCode);
        Map<String, Object> response = new HashMap<>();

        // response.put("message", bodyReqSsoLoginDto);
        // return ResponseEntity.ok(response);
        Set<ConstraintViolation<BodyReqSsoLoginDto>> violations = validator.validate(bodyReqSsoLoginDto);

        if (!violations.isEmpty()) {
            StringBuilder errorMessages = new StringBuilder();  
            for (ConstraintViolation<BodyReqSsoLoginDto> violation : violations) {
                errorMessages.append(violation.getMessage()).append("\n");
            }
            log.info("{} Invalid Body Request SSO {}",uuid,errorMessages);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, redirectUrlUnauthorized)
                    .build();
        }
        response.put("status", "success");
        response.put("message", uuid);
        
        return ResponseEntity.ok(response);

    }
    
}

