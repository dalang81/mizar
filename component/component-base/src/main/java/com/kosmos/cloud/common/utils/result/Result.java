package com.kosmos.cloud.common.utils.result;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.kosmos.cloud.common.code.ResponseCode;
public class Result<T> implements Serializable {
	
	private static String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ";
	private static final long serialVersionUID = -5321325700529744542L;
	private String retCode = ResponseCode.RESPONSE_RETURN_CODE_SUCCESS.getCode();
	private String retMessage = "操作成功";
	private String retTime = "";
	private T retData = null;
	public Result() {
	}
	public Result(String retCode, String retMessage, T retData) {
		this.retCode = retCode;
		this.retMessage = retMessage;
		this.retData = retData;
	}
	
	public Result success() {
		this.retCode = retCode;
		this.retMessage = retMessage;
		this.retTime = getNow();
		return this;
	}

	public Result success(T data) {
		this.retCode = retCode;
		this.retMessage = retMessage;
		this.retData = data;
		this.retTime = getNow();
		return this;
	}
	
	public Result success(String sms, T data) {
		this.retCode = retCode;
		this.retMessage = sms;
		this.retData = data;
		this.retTime = getNow();
		return this;
	}

	public Result success(String statusCode, String sms, T data) {
		if (! StringUtils.isEmpty(statusCode)) {
			this.retCode = statusCode;
		} else {
			this.retCode = retCode;
		}
		if (! StringUtils.isEmpty(sms)) {
			this.retMessage = sms;
		} else {
			this.retMessage = retMessage;
		}
		this.retData = data;
		this.retTime = getNow();
		return this;
	}
	
	public Result failure(String code, String sms) {
		this.retCode = code;
		this.retMessage = sms;
		this.retTime = getNow();
		return this;
	}
	
	public static String getNow() {
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
		return df.format(new Timestamp(System.currentTimeMillis()));
	}
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
	public T getRetData() {
		return retData;
	}
	public void setRetData(T retData) {
		this.retData = retData;
	}
}
