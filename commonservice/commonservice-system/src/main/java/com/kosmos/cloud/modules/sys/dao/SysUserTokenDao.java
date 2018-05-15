package com.kosmos.cloud.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kosmos.cloud.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 * 
 * @author kaka
 *  
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
    
    SysUserTokenEntity queryByUserId(Long userId);

    SysUserTokenEntity queryByToken(String token);
	
}
