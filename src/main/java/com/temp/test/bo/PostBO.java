package com.temp.test.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temp.test.entity.PostEntity;
import com.temp.test.repository.PostRepository;

@Service
public class PostBO {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<PostEntity> getPostList() {
		List<PostEntity> postList = postRepository.findAll();
		return postList;
	}
	
}
