package com.kosmos.cloud.utils;



import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json util类
 * 
 */
public class JsonUtil {

  private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

  private static ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    // mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    mapper.setSerializationInclusion(Include.NON_NULL);  
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static ObjectMapper getObjectMapper() {
    return mapper;
  }
  
	/**
	 * parse and convert a json string to an object
	 * 
	 * <pre>
	 *     MyType a = fromJson("{\"a\": 1, \"b\": 2}", MyType.class);
     *     int[] arr = fromJson("[1, 2]", int[].class);
     *     List list = fromJson("[1, 2]", List.class);
     *     List list = fromJson("[1, 2]", Object.class); 
     *     Map map = fromJson("{\"a\": 1, \"b\": 2}", Map.class); // same as toMap(json);
     *     Map map = fromJson("{\"a\": 1, \"b\": 2}", Object.class); // same as toMap(json);
	 * </pre>
	 * 
	 * @param json
	 * @param t
	 * @return null if there is any exception parsing json string
	 */
    public static <T> T fromJson(String json, Class<T> t) {

      if (json == null) {
          return null;
      }
      try {
          return mapper.readValue(json, t);
      } catch (Exception e) {
          logger.info("Cannot parse json string to Object. Json: <"
                  + json + ">, Object class: <" + t.getName() + ">.", e);
      }
      return null;
    }

	/**
	 * Convert a JsonArray string to a list of Class<T>
	 * <pre>
	 *   List<Integer> l = toList("[1, 2]", Integer.class);
	 *   List<MyType> l = toList("[{"a": 2}, {"a": 3}]", MyType.class);
	 *   List<Map> l = toList("[{"a": 2}, {"a": 3}]", Map.class);
	 *   List<Map> l = toList("[{"a": 2}, {"a": 3}]", Object.class);
	 * </pre>
	 * 
	 * @param json
	 * @param clazz
	 * @return
     * @exception throw IOException if there is invalid json string
	 */
	public static <T> List<T> toList(String json, Class<T> clazz) throws IOException {
    // .constructParametrizedType is introduced from jackson 2.5
	  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
	  return mapper.readValue(json, javaType);
	}

	/**
	 * convert a map to object
	 * 
	 * @param map
	 * @param t
	 * @return
	 */
    public static <T> T fromMap(Map<?, ?> map, Class<T> t) {

        if (map == null) {
            return null;
        }
        try {
            return mapper.readValue(toJsonString(map), t);
        } catch (Exception e) {
            logger.info("Cannot parse map to Object. Map: <"
                    + map + ">, Object class: <" + t.getName() + ">.", e);
        }
        return null;
    }
     
    
    /**
     * convert json object to a Map, with designated element type
     * 
     * @param jsonText
     * @return
     * @exception throw IOException if there is invalid json string
     */
    public static <T> Map<String, T> toMap(String jsonText, Class<T> clazz) throws IOException {
	  JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, String.class, clazz);
	  return mapper.readValue(jsonText, javaType);
    }
    
    public static Map<?, ?> toMap(String jsonText) {
      return fromJson(jsonText, Map.class);
    }
   
    /**
     * convert any object to json string
     * <pre>
     * toJsonString(map) returns{ "b" : "B", "a" : "A" }
     * toJsonString(list) returns ["b", "a"]
     * toJsonString(array) returns ["b", "a"]
     * toJsonString(obj) returns { "fieldA" : "a", "fieldB" : "b" }
     * </pre>
     * 
     * @param obj
     * @return json string
     */
    public static String toJsonString(Object obj) {
        try {
          if (obj != null) {
            return mapper.writeValueAsString(obj);
          }
        } catch (Exception e) {
            logger.warn("Cannot convert to json " + obj);
        }
        return "{}";
    }
    
    public static String toJsonStr(Object obj,boolean ignoreError) {
      try {
        if (obj != null) {
          return mapper.writeValueAsString(obj);
        }
      } catch (Exception e) {
          logger.debug("convert to json error for object: {}",obj,e);
          if(!ignoreError){
            throw new IllegalArgumentException("convert to json error for object",e);
          }
      }
      return null;
    }
}
