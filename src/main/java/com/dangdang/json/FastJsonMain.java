package com.dangdang.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import com.alibaba.fastjson.JSON;

public class FastJsonMain {
	
	private static final Logger logger = LoggerFactory.getLogger(FastJsonMain.class);
	
	public static void main(String[] args) {
		Profiler profile = new Profiler("gson json");
		profile.start("begin");
		
		Person p = new Person("ghost", 120);
		String json = JSON.toJSONString(p);
		
		logger.info("{}", json);
		logger.info("{}",profile.stop());
	}
}
