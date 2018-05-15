package com.kosmos.cloud;

import org.junit.Test;

public class BaseTest {

	@Test
	public void stringReg() {
		String ip = "192.168.1.11";
		String reg = "^192\\.168\\.[0-254]{1,3}\\.[0-254]{1,3}";
		System.out.println(ip.matches(reg));
	}
}
