package com.kosmos.cloud.common.code;

import java.util.HashMap;
import java.util.Map;

import com.kosmos.cloud.common.utils.result.Result;

/**
 * 公共服务响应状态码
 * @author  kosmos 加 technology 
 *
 */
public enum ResponseCode {
	//请求失败、结果处理失败
	RESPONSE_CODE_FAILURE("10000","请求失败、结果处理失败"),
	//请求成功、结果处理成功
	RESPONSE_CODE_SUCCESS("10001","请求成功、结果处理成功"),
	//请求失败、参数格式错误
	RESPONSE_CODE_PARAM_FORMAT_ERROR("10002","请求失败、参数格式错误"),
	//参数错误
	RESPONSE_CODE_PARAM_ERROR("10003","参数错误"),
	//必要的请求参数不能为空
	RESPONSE_CODE_REQ_CANNOT_EMPTY("10004","必要的请求参数不能为空"),
	//用户不存在
	RESPONSE_CODE_USER_DOES_NOT_EXIST("10005","用户不存在"),
	//数据查询成功
	RESPONSE_CODE_QUERY_SUCCESS("10006","数据查询成功"),
	//无数据或者结果
	RESPONSE_CODE_QUERY_NO_DATA("10007","无数据或者结果"),
	//操作成功
	RESPONSE_RETURN_CODE_SUCCESS("200","操作成功"),
	//未登录异常
	RESPONSE_CODE_UNLOGIN_ERROR("421","未登录异常"),
	//无权访问该系统
	RESPONSE_CODE_NO_PERMISSION("403","无权访问该系统"),
	//系统内部异常
	RESPONSE_CODE_SYSTEM_ERROR("500","系统内部异常"),
	//上游服务端网关超时
	RESPONSE_CODE_TIME_OUT("504","上游服务端网关超时"),

	OK("S000A000", null),

	NOT_AUTHORIZED("11001", "鉴权错误"),

	REQUEST_FORBIDDEN("11002","禁止访问"),

	PARAMETER_ERROR("11003", "参数不合法"),

	USER_NOT_EXISTS_ERROR("11004", "用户不存在"),

	USER_EXISTS_ERROR("11005", "用户已存在"),

	SERVICE_ERROR("11006", "服务异常");
	
	private String code; //状态码  
    private String message; //返回消息
    
    // 构造方法  
    private ResponseCode(String code,String message) {  
        this.code = code;  
        this.message = message;  
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

	public static ResponseVO buildEnumResponseVO(ResponseCode vitalResponseCode, Object data) {
		return new ResponseVO(vitalResponseCode.getCode(),vitalResponseCode.getMessage(),data);
	}

	public static Map<String, Object> buildReturnMap(ResponseCode vitalResponseCode, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", vitalResponseCode.getCode());
		map.put("message", vitalResponseCode.getMessage());
//		map.put("version", ResponseVO.VERSION);
		map.put("data", data);
		return map;
	}
}
