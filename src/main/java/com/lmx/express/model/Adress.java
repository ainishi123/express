package com.lmx.express.model;

import lombok.Data;

/**
 * 地址
 * @author 123
 *
 */
@Data
public class Adress {

	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 省编号
	 */
	private String provinceCode;
	
	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 市编号
	 */
	private String cityCode;
	
	/**
	 * 区
	 */
	private String area;

	/**
	 * 区编号
	 */
	private String areaCode;
	
	/**
	 * 街道
	 */
	private String street;
	
	
	/**
	 * 邮编
	 */
	private String zipCode;
	
	/**
	 * 是否为直辖市
	 */
	public boolean isProvinceLevelCity() {
		return province.equals(city);
	}
	
}
