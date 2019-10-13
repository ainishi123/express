package com.lmx.express.model.prediction;

import lombok.Data;

/**
 * 韵达预测结果
 * @author 123
 *
 */
@Data
public class YundaPredictionResult implements PredictionResult{

	/**
	 * 价格
	 */
	private String price;
}
