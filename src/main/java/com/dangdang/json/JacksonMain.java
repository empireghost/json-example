package com.dangdang.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMain {
	
	private static final Logger logger = LoggerFactory.getLogger(JacksonMain.class);
	
	public static void main(String[] args) throws JsonProcessingException {
		Profiler profile = new Profiler("jackson json");
		profile.start("begin");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Person p = new Person("ghost", 120);
		String json = mapper.writeValueAsString(p);
		
		logger.info("{}", json);
		logger.info("{}",profile.stop());
	}
}
