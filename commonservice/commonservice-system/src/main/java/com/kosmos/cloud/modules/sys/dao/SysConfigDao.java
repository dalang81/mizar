package com.kosmos.cloud.modules.sys.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kosmos.cloud.modules.sys.entity.SysConfigEntity;

/**
 * 系统配置信息
 * 
 * @author kaka
 *  
 * @date 2016年12月4日 下午6:46:16
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

	/**
	 * 根据key，查询value
	 */
	SysConfigEntity queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
}
