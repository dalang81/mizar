package com.kosmos.cloud.modules.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmos.cloud.common.utils.PageUtils;
import com.kosmos.cloud.common.utils.Query;
import com.kosmos.cloud.common.utils.R;
import com.kosmos.cloud.modules.sys.entity.SysLogEntity;
import com.kosmos.cloud.modules.sys.service.SysLogService;

import java.util.List;
import java.util.Map;


/**
 * 系统日志
 * 
 * @author kaka
 *  
 * @date 2017-03-08 10:40:56
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysLogEntity> sysLogList = sysLogService.queryList(query);
		int total = sysLogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
}
