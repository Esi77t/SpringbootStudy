package com.example.todo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// SimpleUrlAuthenticationSuccessHandler
// 인증 성공 후 사용자를 처리하는 데 사용되는 클래스
@Component
@Slf4j
@AllArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	// 스프링 시큐리티가 OAuth2 로그인에 성공한 직후 호출하는 메서드
	// Authentication 객체에 로그인 된 사용자가 정보에 포함되어 있다.
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 토큰 성공
		TokenProvider tokenProvider = new TokenProvider();
		String token = tokenProvider.create(authentication);
		
		log.info("token {}", token);
		
		// 프론트엔드로 리다이렉트를 할 수 있겠으나 토큰을 전달할 수는 없다
		// 프론트엔드는 백엔드가 리다이렉트 하면서 전달하는 토큰을 받아주는 기능이 필요하다
		response.sendRedirect("http://localhost:3000");
	}
}
