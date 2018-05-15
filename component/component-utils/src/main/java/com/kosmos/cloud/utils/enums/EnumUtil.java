/**
 * 
 */
package com.kosmos.cloud.utils.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kaka
 * enum util 类
 */
public class EnumUtil {

	private static Logger LOG = LoggerFactory.getLogger(EnumUtil.class);
	/**
	 * 根据enum 的value 获取enum 对象
	 * @param clazz
	 * @param value
	 * @throws RuntimeException 如果没有找到value对应的enum
	 * @return
	 */
	public static <T extends Enum<T> & OmniEnum<?>> T parseByValue(Class<T> clazz, Object value) {
	    for (T enumValue : clazz.getEnumConstants()) {
	      if (enumValue.value().equals(value)) {
	        return enumValue;
	      }
	    }
	    throw new RuntimeException(
	        String.format("No enum value for %s found from class %s", value, clazz.getSimpleName()));
    }

	/**
	 * 根据enum 的value 获取enum 对象
	 * @param clazz
	 * @param value
	 * @return 如果没有找到value对应的enum, 则返回null
	 */
	public static <T extends Enum<T> & OmniEnum<?>> T parseByValueOrNull(Class<T> clazz, Object value) {
	    for (T enumValue : clazz.getEnumConstants()) {
	      if (enumValue.value().equals(value)) {
	        return enumValue;
	      }
	    }
	    LOG.warn(
	        String.format("No enum value for %s found from class %s, will return null", value, clazz.getSimpleName()));
	    return null;
    }
	
	/**
	 * 根据enum的name 获取 enum 对象
	 * @param clazz
	 * @param name
	 * @throws RuntimeException 如果没有找到name对应的enum
	 * @return
	 */
	public static <T extends Enum<T>> T parseByName(Class<T> clazz, String name)
	{
		  for(T enumValue : clazz.getEnumConstants()){
			  if(enumValue.name().equals(name))
			  {
				  return enumValue;
			  }
		  }
		  throw new RuntimeException(
		  String.format("No enum name for %s found from class %s", name, clazz.getSimpleName()));
    }
	
	/**
	 * 根据enum的name 获取 enum 对象
	 * @param clazz
	 * @param name
	 * @return 如果没有找到name对应的enum, 则返回null
	 */
	public static <T extends Enum<T>> T parseByNameOrNull(Class<T> clazz, String name)
	{
		  for(T enumValue : clazz.getEnumConstants()){
			  if(enumValue.name().equals(name))
			  {
				  return enumValue;
			  }
		  }
		  LOG.warn(String.format("No enum name for %s found from class %s", name, clazz.getSimpleName()));
		  return null;
    }
	
	/**
	 * 根据enum列表返回对应值的列表
	 * @param enums
	 * @return
	 */
	public static <T> List<T> getValues(List<? extends OmniEnum<T>> enums) {
	    List<T> result = new ArrayList<>();
	    for (OmniEnum<T> enumObject : enums) {
	      result.add(enumObject.value());
	    }
	    return result;
    }
	
	/**
	 * 获取 enum 的描述
	 * @param item
	 * @return 如果不存在则返回null
	 */
	public static String getDescription(OmniEnum<?> item)
	{
		try {
			Field field = item.getClass().getField(item.toString());
			return getDescription(field);
		} catch (Exception e)
		{
			LOG.warn(String.format("Can not get description of %s", item.getClass().getSimpleName()));
			return null;
		}		
	}
	
	/**
	 * 获取一个field的描述
	 * @param field
	 * @return
	 */
	public static String getDescription(Field field)
	{
		Description desc = field.getAnnotation(Description.class);
	    if(desc == null)
	    	return null;
	    return desc.value();
	}
}
