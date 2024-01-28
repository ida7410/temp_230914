package com.temp.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temp.test.bo.PostBO;
import com.temp.test.entity.PostEntity;

@RequestMapping("/test")
@Controller
public class TestController {
	
	@Autowired
	private PostBO postBO;
	
	@ResponseBody
	@RequestMapping("/1")
	public String test1() {
		return "Hello World!";
	}
	
	@ResponseBody
	@RequestMapping("/2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("test", "Hello World");
		return map;
	}
	
	@RequestMapping("/3")
	public String test3() {
		return "test/test";
	}
	
	@RequestMapping("/4")
	@ResponseBody
	public List<PostEntity> test4() {
		return postBO.getPostList();
	}
	
}
