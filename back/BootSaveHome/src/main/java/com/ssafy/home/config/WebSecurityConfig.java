//package com.ssafy.home.config;
//
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.stereotype.Component;
//
//import com.ssafy.home.util.JwtAuthenticationFilter;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Configuration
//@Slf4j
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	private final JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors()
//			.and()
//			.csrf()
//			.disable()
//			.httpBasic()
//			.disable()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.authorizeHttpRequests()
//			.antMatchers("/", "/member").permitAll()
//			.anyRequest()
//			.authenticated();
//		
//		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
//	}
//}
