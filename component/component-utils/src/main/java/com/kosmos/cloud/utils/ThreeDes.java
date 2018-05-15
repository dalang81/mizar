package com.kosmos.cloud.utils;

import java.io.ByteArrayOutputStream;
import java.security.Security;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 与数通信息接口加密算法
* @author kaka
*
 */
public class ThreeDes {
	
	static{
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}
	
	public static final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
		0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
		0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
		(byte) 0xE2 };
	
	public static int getSeqId(){		
		Random random = new Random();
		return Math.abs(random.nextInt());
	}

	private static final String Algorithm = "DESede"; // algorithm/mode/padding
	private static final String amp = "DESede/ECB/PKCS5Padding";


	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(amp);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(amp);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

		
	/** 16进制数字字符集  */ 
	private   static   String   hexString= "0123456789ABCDEF"; 
	
	/** 
	  *   将字符串编码成16进制数字,适用于所有字符（包括中文） 
	  */ 
	public static String byte2hex(byte[] bytes) { 
		StringBuffer   sb=new   StringBuffer(bytes.length*2); 
		for(int i=0;i <bytes.length;i++){ 
			sb.append(hexString.charAt((bytes[i]&0xf0)>> 4)); 
			sb.append(hexString.charAt((bytes[i]&0x0f)>> 0)); 
		} 
		return   sb.toString(); 
	} 
	
	/**将每2位16进制整数组装成一个字节
	 * 
	 * @param bytes
	 * @return
	 */ 
	public  static  byte[]  hex2byte(String hex) { 
		ByteArrayOutputStream baos=new ByteArrayOutputStream(hex.length()/2); 
		for(int   i=0;i <hex.length();i+=2) 
			baos.write((hexString.indexOf(hex.charAt(i)) <<4  |hexString.indexOf(hex.charAt(i+1)))); 
		return  baos.toByteArray(); 
	}
	
	/**
	 * 取得加密后数据
	 * @param szSrc
	 * @return
	 */
	public static String getDes(String szSrc) {
		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());		
		String hex = byte2hex(encoded);
		return hex;
	}


	/**
	 * 取得解密后数据
	 * @param hex
	 * @return
	 */
	public static String getUnDes(String hex) {
		byte [] ob = hex2byte(hex);
		
		byte[] srcBytes = decryptMode(keyBytes, ob);
		return new String(srcBytes);
	}
	
	/**
	 * 根据密钥取得加密后数据
	 * @param szSrc
	 * @return
	 */
	public static String getDes(String szSrc,String keys) {
		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	    byte[]  tmpKeys= Arrays.copyOf(keys.getBytes(), 24);		
		byte[] encoded = encryptMode(tmpKeys, szSrc.getBytes());		
		String hex = byte2hex(encoded);
		return hex;
	}
	
	/**
	 * 根据密钥取得解密后数据
	 * @param hex
	 * @return
	 */
	public static String getUnDes(String hex,String keys) {
		byte [] ob = hex2byte(hex);
		byte[]  tmpKeys= Arrays.copyOf(keys.getBytes(), 24);	
		byte[] srcBytes = decryptMode(tmpKeys, ob);
		return new String(srcBytes);
	}
}
