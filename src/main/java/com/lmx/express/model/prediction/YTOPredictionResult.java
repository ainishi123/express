package com.lmx.express.model.prediction;

import java.util.List;

import lombok.Data;

/**
 * 圆通快递 价格预测结果
 * 
 * @author 123
 *
 */
@Data
public class YTOPredictionResult implements PredictionResult {
	
	/**
	 * 预测结果列表
	 */
	private List<PredictionItem> items;

	/**
	 * 预测结果
	 * @author 123
	 *
	 */
	@Data
	public static class PredictionItem{
		
		/**
		 * 币种
		 */
		private String currency;
		
		private String effectiveTypeCode;
		
		/**
		 * 服务类型子类（计时达次日）
		 */
		private String effectiveTypeName;
		
		/**
		 * 
		 */
		private String expType;
		
		/**
		 * 服务类型（承诺达特快 等）
		 */
		private String expTypeName;
		/**
		 * 预计运费
		 */
		private String expenses;
		
		/**
		 * 预计到付
		 */
		private String expensesReceive;
		
		/**
		 * 预计时间（工作日）
		 */
		private String period;
	}
}
