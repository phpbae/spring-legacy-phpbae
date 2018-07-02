package com.phpbae.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;

@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/security-web/main").permitAll() //main.jsp 로그인하지 않고 전부 접근 가능
                .antMatchers(HttpMethod.GET,"/security-web/admin").hasAuthority("ROLE_ADMIN") //admin은 ROLE_ADMIN에 설정된 사용자 로그인한 경우에만 가능
                .anyRequest().authenticated() //나머지 이외 요청은 인증된 사용자인 경우에만 가능.
                .and()
                .formLogin().defaultSuccessUrl("/security-web/main").permitAll() //인증방식은 formLogin 방식 채용
                .and()
                .logout().logoutUrl("/security-web/logout").logoutSuccessUrl("/security-web/main")
                .and()
                .csrf().disable(); //CSRF 대책기능 사용안함.

        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //다음 사용자를 만들어, 메모리상에서 관리.
        auth.inMemoryAuthentication()
                .withUser("user").password("user").authorities("ROLE_USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN"); //roles 라고 하면, ROLE_ 가 자동으로 추가되나보다.

        super.configure(auth);
    }
}
