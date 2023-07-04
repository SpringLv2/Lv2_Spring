package com.sparta.spring_lv1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Spring 서버가 뜰 때 Spring IoC Container에 Bean으로 저장된다
@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        /** BCryptPasswordEncoder
         * 스프링 시큐리티에서 제공하는 패스워드 인코더로 알고리즘을 이용하여 패스워드의 암호화를 진행한다.
         * 로그인시 입력한 패스워드와 저장된 암호화된 패스워드를 비교하여 유효성검사를 진행한다.
         * 보안 강도가 높아 일반적으로 사용하는 해시 알고리즘이다.
         */

    }
}