/**
 * 
 */
package com.kosmos.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosmos.cloud.bean.User;
import com.kosmos.cloud.service.IUserMgrService;
import com.kosmos.cloud.service.IUserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserMgrServiceImpl implements IUserMgrService {

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userService;
	
	@Override
	public User getUserByName(String userName) throws Exception {
		return userService.getUserByName(userName);
	}

	@Transactional
	@Override
	public boolean deleteUserById(String userId) throws Exception {
		boolean flag = userService.deleteById(userId);
		return flag;
	}
}
