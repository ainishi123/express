package com.lmx.express.model.prediction;

import java.util.Date;

import lombok.Data;

/**
 * 预测结果
 * @author 123
 */
@Data
public class STOPredictionResult implements PredictionResult{

	/**
	 * 到达时间
	 */
	private Date reachTime;
	
	/**
	 * 价格（单位元）
	 */
	private String price;
}
