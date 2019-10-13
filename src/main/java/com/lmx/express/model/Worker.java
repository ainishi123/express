package com.lmx.express.model;

import lombok.Data;

/**
 * 工作人员
 * @author 123
 *
 */
@Data
public class Worker {

	/**
	 * 名字
	 */
	private String name;
	
	/**
	 * 联系电话
	 */
	private String phone;
	
	/**
	 * 当前位置（维度）
	 */
	private String locationLat;
	
	/**
	 * 当前位置（经度）
	 */
	private String locationLng;
}
