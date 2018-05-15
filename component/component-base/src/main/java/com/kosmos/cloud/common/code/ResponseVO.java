package com.kosmos.cloud.common.code;

public class ResponseVO {
	//版本号
	public static final String VERSION = "v1.0";
	//状态码
	private String code;
	//返回消息
	private String message;
	//版本号
	public String version = VERSION;
	//data对象
	private Object data;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResponseVO() {
		super();
	}
	
	public ResponseVO(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
