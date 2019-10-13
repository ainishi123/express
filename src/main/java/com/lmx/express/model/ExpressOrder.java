package com.lmx.express.model;

import java.util.Date;

import lombok.Data;

/**
 * 快递单
 * @author 123
 *
 */
@Data
public class ExpressOrder {
	
	/**
	 * 邮递员
	 */
	private Worker postman;
	
	/**
	 * 寄件人
	 */
	private Customer sender;
	
	/**
	 * 收件人
	 */
	private Customer addressee;
	
	/**
	 * 邮寄时间
	 */
	private Date postTime;
	
	/**
	 * 打包揽件时间
	 */
	private Date packageTime;
}
