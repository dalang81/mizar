package com.kosmos.cloud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class PropertyUtils {
	private static Properties properties = new Properties();

	static {
		try {
		    properties.load(new InputStreamReader(new FileInputStream(new File("resources.properties")), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static int getPropertyAsInt(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}
}
