package com.kosmos.cloud.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kosmos.cloud.modules.sys.entity.SysLogEntity;

/**
 * 系统日志
 * 
 * @author kaka
 *  
 * @date 2017-03-08 10:40:56
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
