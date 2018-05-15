package com.kosmos.cloud.code;

import java.util.HashMap;
import java.util.Map;

import com.kosmos.cloud.common.code.ResponseVO;

/**
 * 用户服务Response Code
 * @author kaka
 *
 */
public enum UserResponseCode  {
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
	
	
	//没有找到oauth_accesstoken相关记录
	RESPONSE_CODE_OAUTH_ACCESSTOKEN_EMPTY("20001","没有找到oauth_accesstoken相关记录"),
	//用户名不能为空
	RESPONSE_CODE_USER_NAME_NOT_EMPTY("20002","用户名不能为空"),
	//用户密码不能为空
	RESPONSE_CODE_USER_PASSWORD_NOT_EMPTY("20003","用户密码不能为空"),
	//用户密码不匹配
	RESPONSE_CODE_USER_PASSWORD_NOT_VALIDATE("20004","用户密码不匹配"),
	//用户输入的旧密码不能为空
	RESPONSE_CODE_USER_OLD_PASSWORD_NOT_EMPTY("20005","用户输入的旧密码不能为空"),
	//用户输入的新密码不能为空
	RESPONSE_CODE_USER_NEW_PASSWORD_NOT_EMPTY("20006","用户输入的新密码不能为空"),
	//用户输入的旧密码不正确
	RESPONSE_CODE_USER_OLD_PASSWORD_NOT_VALIDATE("20007","用户输入的旧密码不正确"),
	//用户的联系方式不能为空
	RESPONSE_CODE_USER_MOBILE_OR_TELEPHONE_NOT_EMPTY("20008","用户的联系方式不能为空"),
	//用户的验证码不能为空
	RESPONSE_CODE_USER_VERICODE_NOT_EMPTY("20009","用户的验证码不能为空"),
	//验证码已发送您的手机，不能重复获取
	RESPONSE_CODE_USER_VERICODE_NOT_REPEAT_GET("20010","验证码已发送您的手机，不能重复获取"),
	//用户已存在
	RESPONSE_CODE_USER_NAME_REPEATED("20011","该用户已存在"),
	//短信验证码错误，请重新输入
	RESPONSE_CODE_USER_VERICODE_FALSE("20012","短信验证码错误，请重新输入"),
	//提交的积分数量与系统设置不符
	RESPONSE_CODE_INTEGRAL_ERROR("20013","提交的积分数量与系统设置不符，请重新提交")
	;
	
	
	
	//状态码  
	private String code; 
	//返回消息
    private String message; 
    
    // 构造方法  
    private UserResponseCode(String code,String message) {  
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

	public static ResponseVO buildEnumResponseVO(UserResponseCode userResponseCode, Object data) {
		return new ResponseVO(userResponseCode.getCode(),userResponseCode.getMessage(),data);
	}
	
	public static Map<String, Object> buildReturnMap(UserResponseCode fansResponseCode, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", fansResponseCode.getCode());
		map.put("message", fansResponseCode.getMessage());
		map.put("data", data);
		return map;
	}
}
