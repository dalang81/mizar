package com.kosmos.cloud.utils;



import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class ByteUtil {

  /**
   * Base64编码
   * 
   * @param bstr
   * @return String
   */
  public static String base64Encode(byte[] bstr) {
    return Base64.encodeBase64String(bstr);
  }

  /**
   * Base64编码, 没有回车符
   * 
   * @param bstr
   * @return String
   */
  public static String base64EncodeNoChunk(byte[] bstr) {
    try {
      return new String(Base64.encodeBase64(bstr, false), "ASCII");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Base64解码
   * 
   * @param str
   * @return string
   */
  public static byte[] base64Decode(String str) {
    return Base64.decodeBase64(str);
  }

  /**
   * 将16进制的字符串转换成bytes
   * 
   * @param hex
   * @return 转化后的byte数组
   */
  public static byte[] hexToBytes(String hex) {
    return hexToBytes(hex.toCharArray());
  }

  /**
   * 将16进制的字符数组转换成byte数组
   * 
   * @param hex
   * @return 转换后的byte数组
   */
  public static byte[] hexToBytes(char[] hex) {
    int length = hex.length / 2;
    byte[] raw = new byte[length];
    for (int i = 0; i < length; i++) {
      int high = Character.digit(hex[i * 2], 16);
      int low = Character.digit(hex[i * 2 + 1], 16);
      int value = (high << 4) | low;
      if (value > 127)
        value -= 256;
      raw[i] = (byte) value;
    }
    return raw;
  }

  /**
   * 将byte数组转换成16进制字符串
   * 
   * @param bytes
   * @return 16进制字符串
   */
  public static String bytesToHex(byte[] bytes) {
    String hexArray = "0123456789abcdef";
    StringBuilder sb = new StringBuilder(bytes.length * 2);
    for (byte b : bytes) {
      int bi = b & 0xff;
      sb.append(hexArray.charAt(bi >> 4));
      sb.append(hexArray.charAt(bi & 0xf));
    }
    return sb.toString();
  }
}
