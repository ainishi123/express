package com.lmx.express.model;

import lombok.Data;

@Data
public class Item {

	/**
	 * 长度（cm）
	 */
	private String length;
	
	/**
	 * 重量（kg）
	 */
	private String weight;
	
	/**
	 * 宽度（cm）
	 */
	private String width;
	
	/**
	 * 高度（cm）
	 */
	private String height;
}
