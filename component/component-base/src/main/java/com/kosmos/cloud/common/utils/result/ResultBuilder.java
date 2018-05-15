package com.kosmos.cloud.common.utils.result;

import com.kosmos.cloud.common.code.ResponseCode;

public class ResultBuilder<T> {
	
	private String retCode = ResponseCode.OK.getCode();
	private String retMessage = "OK";
	private T retData = null;
	public ResultBuilder<T> code(String retCode) {
		this.retCode = retCode;
		return this;
	}
	public ResultBuilder<T> message(String retMessage) {
		this.retMessage = retMessage;
		return this;
	}
	public ResultBuilder<T> data(T retData) {
		this.retData = retData;
		return this;
	}
	
	public ResultBuilder<T> init(String retCode, String retMessage, T retData) {
		this.retCode = retCode;
		this.retMessage = retMessage;
		this.retData = retData;
		return this;
	}
	public Result<T> build() {
		return new Result<T>(this.retCode, this.retMessage, this.retData);
	}
}
