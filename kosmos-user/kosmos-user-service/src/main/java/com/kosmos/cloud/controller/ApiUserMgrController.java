package com.kosmos.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.cloud.bean.User;
import com.kosmos.cloud.code.UserResponseCode;
import com.kosmos.cloud.common.code.ResponseVO;
import com.kosmos.cloud.service.IUserMgrService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class ApiUserMgrController {
	

	@Autowired
	@Qualifier("userMgrServiceImpl")
	private IUserMgrService userMgrService;
	
	/**
	 * 根据ID删除登录信息user表信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(path = "/user/delete/{id}", method = RequestMethod.DELETE)
	public ResponseVO deleteUser(@PathVariable(value = "id") String id,@RequestHeader(value = "userId", required = true) Long userId) {
		if(StringUtils.isEmpty(id)){
			return UserResponseCode.buildEnumResponseVO(UserResponseCode.RESPONSE_CODE_REQ_CANNOT_EMPTY, null);
		}
		
		try {
			userMgrService.deleteUserById(id);
			return UserResponseCode.buildEnumResponseVO(UserResponseCode.RESPONSE_RETURN_CODE_SUCCESS, id);
		} catch (Exception e) {
			return UserResponseCode.buildEnumResponseVO(UserResponseCode.RESPONSE_CODE_SYSTEM_ERROR, id);
		}
	}
	
	@RequestMapping(path = "/user/get/{userName}", method = RequestMethod.GET)
	public ResponseVO getUser(@PathVariable(value = "userName") String userName) {
		Map<String, Object> returnData = null;
		try {
			User user = userMgrService.getUserByName(userName);
			if (null != user) {
				returnData = new HashMap<String, Object>();
				returnData.put("user", user);
				return UserResponseCode.buildEnumResponseVO(UserResponseCode.RESPONSE_CODE_SUCCESS, returnData);
			}
			return UserResponseCode.buildEnumResponseVO(UserResponseCode.RESPONSE_CODE_SYSTEM_ERROR, null);
		} catch (Exception e) {
			return UserResponseCode.buildEnumResponseVO(UserResponseCode.RESPONSE_CODE_SYSTEM_ERROR, null);
		}
		
	}
}