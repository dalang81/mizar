package com.kosmos.cloud.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 集合类操作相关的工具类
 * @author kaka
 *
 */
public class CollectionUtil {
	public static boolean isBlank(Object[] objArray) {
		return (objArray == null) || (objArray.length <= 0);
	}

	public static boolean isNotBlank(Object[] objArray) {
		return !isBlank(objArray);
	}

	public static boolean isBlank(Object obj) {
		return (obj == null) || ("".equals(obj));
	}

	public static boolean isNotBlank(Object obj) {
		return !isBlank(obj);
	}

	public static boolean isBlank(Collection<Object> collection) {
		return (collection == null) || (collection.size() <= 0);
	}

	public static boolean isNotBlank(Collection<Object> collection) {
		return !isBlank(collection);
	}

	public static boolean isBlank(Set<Object> setObj) {
		return (setObj == null) || (setObj.size() <= 0);
	}

	public static boolean isNotBlank(Set<Object> setObj) {
		return !isBlank(setObj);
	}

	public static boolean isBlank(Serializable obj) {
		return obj == null;
	}

	public static boolean isNotBlank(Serializable obj) {
		return !isBlank(obj);
	}

	public static boolean isBlank(Map<Object, Object> mapObj) {
		return (mapObj == null) || (mapObj.size() <= 0);
	}

	public static boolean isNotBlank(Map<Object, Object> mapObj) {
		return !isBlank(mapObj);
	}
}
