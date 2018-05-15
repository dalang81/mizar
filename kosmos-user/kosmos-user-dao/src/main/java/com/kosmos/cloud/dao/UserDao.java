package com.kosmos.cloud.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kosmos.cloud.bean.User;

public interface UserDao extends BaseMapper<User> {
	
	public User getUserByName(Map map);
	
}