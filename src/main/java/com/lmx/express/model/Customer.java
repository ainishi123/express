package com.lmx.express.model;

import lombok.Data;

/**
 * 顾客
 * @author 123
 *
 */
@Data
public class Customer {

	/**
	 * 名字
	 */
	private String name;
	
	/**
	 * 电话
	 */
	private String phone;
	
	/**
	 * 联系 地址
	 */
	private Adress adress;
}
