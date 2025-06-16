package com.korea.rnBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.rnBoard.domain.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}









