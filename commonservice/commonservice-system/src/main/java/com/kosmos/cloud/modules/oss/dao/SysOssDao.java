package com.kosmos.cloud.modules.oss.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kosmos.cloud.modules.oss.entity.SysOssEntity;
import com.kosmos.cloud.modules.sys.dao.BaseDao;

/**
 * 文件上传
 * 
 * @author kaka
 *  
 * @date 2017-03-25 12:13:26
 */
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
	
}
