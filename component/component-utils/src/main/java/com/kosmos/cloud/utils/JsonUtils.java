package com.kosmos.cloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	private static final  Logger log = LoggerFactory.getLogger(JsonUtils.class);
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			
			log.warn("toJson exception", e);
			
			return null;
		}
	}
	
	public static <T> T load(String filePath, Class<T>  clazz) {
//		InputStream is = JsonUtils.class.getResourceAsStream(filePath);
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return load(is, clazz);
	}
	
	public static <T> T load(InputStream is, Class<T>  clazz) {
		
		try {
			T  t = mapper.readValue(is, clazz);
			return t;
		} catch (JsonParseException e) {
			log.warn("JsonParseException", e);
		} catch (JsonMappingException e) {
			log.warn("JsonMappingException", e);
		} catch (IOException e) {
			log.warn("IOException", e);
		}
		
		return null;
	}
	
	public static <T> T parser(String json,Class<T> clzz){
        try {
            T t = mapper.readValue(json, clzz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
	    return null;
	}
}
