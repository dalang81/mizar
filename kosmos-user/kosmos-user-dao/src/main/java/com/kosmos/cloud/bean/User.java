package com.kosmos.cloud.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

/**
 * 用户基本信息
 * @author kaka
 *
 */
@Data
@TableName("sys_user")
public class User{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7152258765773677565L;
	/**
     * 用户ID
     */
    @TableId("user_id")
	private long id;
    /**
     * 用户名
     */
	@TableField("username")
	private String userName;
   
    /**
     * 用户密码
     */
	@TableField("password")
	private String password;
}
