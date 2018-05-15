package com.kosmos.cloud.service;

import com.kosmos.cloud.bean.User;

public interface IUserMgrService {

	public User getUserByName(String userName) throws Exception;
	
	public boolean deleteUserById(String userId) throws Exception;

}
