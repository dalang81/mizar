package com.kosmos.cloud.common.bean;

import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@Data
public class BaseBean {
	
	@TableId(type = IdType.AUTO)
	@TableField("id")
	private String id;
	
	private Date createDate;	// 创建日期
	
	private Date updateDate;	// 更新日期
	
	/**
	 * 当前页
	 */
	@TableField(exist = false)
	private int current=1;
	
	/**
	 * 分页开始下标
	 */
	@TableField(exist = false)
	private int startIndex;
	
	/**
	 * 每页显示条数
	 */
	@TableField(exist = false)
	private int size;
	
	/**
	 * 是否是新记录
	 */
	@TableField(exist = false)
	private boolean isNewRecord = false;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 添加之前执行方法，需要手动调用
	 */
	public void preInsert(){
		if (!this.isNewRecord){
			setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}

		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){
		this.updateDate = new Date();
	}
}
