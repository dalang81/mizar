package com.kosmos.cloud.service;

import com.baomidou.mybatisplus.service.IService;
import com.kosmos.cloud.bean.User;


public interface IUserService extends IService<User> {
	
	public User getUserByName(String userName);
	
}
