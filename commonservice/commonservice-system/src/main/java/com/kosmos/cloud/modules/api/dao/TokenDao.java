package com.kosmos.cloud.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kosmos.cloud.modules.api.entity.TokenEntity;
import com.kosmos.cloud.modules.sys.dao.BaseDao;

/**
 * 用户Token
 * 
 * @author kaka
 *  
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface TokenDao extends BaseDao<TokenEntity> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
