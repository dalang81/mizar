package com.kosmos.cloud.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kosmos.cloud.modules.job.entity.ScheduleJobLogEntity;
import com.kosmos.cloud.modules.sys.dao.BaseDao;

/**
 * 定时任务日志
 * 
 * @author kaka
 *  
 * @date 2016年12月1日 下午10:30:02
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
