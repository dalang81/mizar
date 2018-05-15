package com.kosmos.cloud.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kosmos.cloud.bean.User;
import com.kosmos.cloud.dao.UserDao;
import com.kosmos.cloud.service.IUserService;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

	

	@Override
	public User getUserByName(String userName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		return baseMapper.getUserByName(map);
	}
	
}
