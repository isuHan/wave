package com.wave.member.config;

import com.wave.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//스프링 시큐리티: 스프링 기반의 애플리케이션 보안(인증, 권한 인가 등)을 담당하는 스프링 하위 프레임워크
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    //http 요청에 대한 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login")  //로그인 페이지 url 을 설정한다
                .defaultSuccessUrl("/")       //로그인 성공 시 이동할 url 을 설정한다
                .usernameParameter("email")   //로그인 시 사용할 파라미터 이름으로 email 을 지정한다
                .failureUrl("/members/login/error") //로그인 실패 시 이동한 url 을 설정한다
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))  //로그아웃 url 을 설정한다
                .logoutSuccessUrl("/");      //로그아웃 성공 시 이동할 url 을 설정한다.
        return http.build();
    }


    //db에 비밀번호가 암호화되어 저장된다
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //Spring Security 에서 인증은 AuthenticationManager 를 통해 이루어진다.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    // Security 무시하기
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
