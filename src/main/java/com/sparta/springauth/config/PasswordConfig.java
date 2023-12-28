package com.sparta.springauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig { // passwordConfig
    @Bean
    public PasswordEncoder passwordEncoder() { // passwordEncoder
        return new BCryptPasswordEncoder(); // 비밀번호 암호화 해주는 함수 - Hash함수
    }
}
