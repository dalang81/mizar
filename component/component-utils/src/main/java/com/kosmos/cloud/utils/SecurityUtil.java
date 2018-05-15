package com.kosmos.cloud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityUtil {
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	public static final String key = "kosmos.cloud";
	
	public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            case 10:
            case 13:
                break;
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
	
	public static String filterHtml(String source) {   
        Pattern pattern = Pattern.compile("<([^>]*)>");   
        Matcher matcher = pattern.matcher(source);   
        StringBuffer sb = new StringBuffer();   
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }   
	
	/**
    * 获取一定长度的随机字符串
    * @param length 指定字符串长度
    * @return 一定长度的字符串
    */
   public static String getRandomString(int length) {
       String base = "abcdefghijklmnopqrstuvwxyz0123456789";
       Random random = new Random();
       StringBuffer sb = new StringBuffer();
       for (int i = 0; i < length; i++) {
           int number = random.nextInt(base.length());
           sb.append(base.charAt(number));
       }
       return sb.toString();
   }
   
   public static String getAccessToken(Integer userId) {
	   return ThreeDes.getDes(userId + dateFormat.format(new Date()) + String.valueOf((Math.random() * 9000 + 1000)).substring(0, 3),key);
   }
}
