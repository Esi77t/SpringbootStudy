package com.example.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.todo.model.UserEntity;
import com.example.todo.persistance.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
// 스프링 시큐리티의 OAuth2 인증 과정을 커스터마이징하기 위해 사용하는
// OAuth2 사용자 정보처리 서비스 클래스

// DefaultOAuth2UserService
// 사용자 정보를 처리하는 기본 구현 클래스
// github, 구글 등 외부 인증 제공자를 통해 로그인하면, 이 클래스가 access token을 사용해 사용자 정보를 요청하고,
// OAuth2User 객체로 반환한다
public class OAuthUserServiceImpl extends DefaultOAuth2UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 부모 생성자 호출
	public OAuthUserServiceImpl() {
		super();
	}
	
	// OAuth2 로그인 후 액세스 토큰을 받은 다음, 사용자 정보를 가져오는 핵심 메서드
	// userRequest의 내용
	// accessToken : OAuth2 인증 서버가 발급한 토큰
	// clientRegistration : application.yml에 등록한 github 등 설정 정보
	// additionalParameters : 인증 응답에 포함된 기타 파라미터들
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		final OAuth2User oAuth2User = super.loadUser(userRequest);
		try {
			log.info("OAuth2User attributes {} ",new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// github에서는 login필드가 사용자의 ID에 해당
		// login 필드를 가져온다
		final String username = (String)oAuth2User.getAttributes().get("login");
		// 어떤 인증 제공자로 로그인 했는지 구분하는 문자열
		final String authProvider = userRequest.getClientRegistration().getClientName();
		
		UserEntity userEntity = null;
		
		if(!userRepository.existsByUsername(username)) {
			// 유저가 존재하지 않는다면 새로 만든다
			userEntity = UserEntity.builder()
					.username(username)
					.authProvider(authProvider)
					.build();
			
			// 데이터베이스에 저장
			userEntity = userRepository.save(userEntity);
		}
		log.info("Successfully pulled user info username {} authProvider {}", username, authProvider);

		return oAuth2User;
	}
}
