package com.kosmos.cloud.modules.api.service;


import java.util.Map;

import com.kosmos.cloud.modules.api.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author kaka
 *  
 * @date 2017-03-23 15:22:07
 */
public interface TokenService {

	TokenEntity queryByUserId(Long userId);

	TokenEntity queryByToken(String token);
	
	void save(TokenEntity token);
	
	void update(TokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(long userId);

}
