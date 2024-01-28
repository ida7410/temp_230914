package com.temp.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.temp.test.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
}
