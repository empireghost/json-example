package com.dangdang.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.profiler.Profiler;

import com.google.gson.Gson;

public class GsonMain {
	private static final Logger logger = LoggerFactory.getLogger(GsonMain.class);
	
	public static void main(String[] args) {
		Profiler profile = new Profiler("gson json");
		profile.start("begin");
		Gson gson = new Gson();
		Person p = new Person("ghost", 120);
		String json = gson.toJson(p);
		
		logger.info("{}", json);
		logger.info("{}",profile.stop());
	}
}
