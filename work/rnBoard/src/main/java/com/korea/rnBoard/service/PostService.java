package com.korea.rnBoard.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.korea.rnBoard.domain.PostEntity;
import com.korea.rnBoard.dto.PostDTO;
import com.korea.rnBoard.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //final이나 @nonull로 정의된 필드를 매개변수로 갖는 생성자를 만들어 준다.
public class PostService {
	
	//Repository 생성자 주입하기
	private final PostRepository postRepository;

	//전체조회
	public List<PostDTO> getAllPosts() {
		return postRepository.findAll()
				.stream().map(PostDTO::fromEntity)
				.toList();
	}


	//단건조회
	public List<PostDTO> findById(Long id) {
		Optional<PostEntity> entity = postRepository.findById(id);
		//Optional : null을 안전하게 다루기 위한 Wrapper클래스
		//null값으로 발생하는 에러를 방지하고
		//값이 있을수도, 없을수도 있는 객체를 명확하게 표현하는 방법
		PostDTO dto = null;
		if(entity.isPresent()) {
			dto = PostDTO.fromEntity(entity.get());
		}
		
		return Arrays.asList(dto);
		
	}


	//게시글 추가
	public List<PostDTO> createPost(PostDTO dto) {
		PostDTO result = PostDTO.fromEntity(
						postRepository.save(
								PostDTO.toEntity(dto)));  
		return Arrays.asList(result);
	}
	
	
	
	
	
}
