package com.kosmos.cloud.common.exception;

public class CommonException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2331522211615118754L;
	
	private String code;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public CommonException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String toString() {
		String s = getClass().getName();
		String message = getLocalizedMessage();
		return new StringBuilder().append("exception code:").append(this.code).append(",")
				.append(message != null ? new StringBuilder().append(s).append(": ").append(message).toString() : s)
				.toString();
	}
}
