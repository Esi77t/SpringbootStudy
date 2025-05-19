package com.example.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDTO;
import com.example.board.model.BoardEntity;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository repository;
	
	public List<BoardDTO> getAllPosts() {
		return repository.findAll().stream().map(BoardDTO::fromEntity).collect(Collectors.toList());
	}

	public List<BoardDTO> createPost(BoardDTO dto) {
		BoardEntity entity = BoardDTO.fromDTO(dto);
		repository.save(entity);
		
		return getAllPosts();
	}

	public boolean deletePost(long id) {
		Optional<BoardEntity> option = repository.findById(id);
		if(option.isPresent()) {
			repository.delete(option.get());
			return true;
		} else {
			return false;
		}
	}

	public BoardDTO findPostById(long id) {
		Optional<BoardEntity> option = repository.findById(id);
		
		BoardEntity list = option.get();
		
		return BoardDTO.fromEntity(list);
	}

	public boolean upadatePost(long id, BoardDTO dto) {
		Optional<BoardEntity> option = repository.findById(id);
		if(option.isPresent()) {
			BoardEntity entity = option.get();
			entity.setTitle(dto.getTitle());
			entity.setAuthor(dto.getAuthor());
			entity.setContent(dto.getContent());
			entity.setWritingTime(dto.getWritingTime());
			repository.save(entity);
			return true;
		} else {
			return false;
		}
		
	}


}
