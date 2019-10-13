package com.lmx.express.model.prediction;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class SFPredictionResult implements PredictionResult{

	private List<CargoTypeItem> cargoItems = new ArrayList<SFPredictionResult.CargoTypeItem>();
	
	@Data
	public static class CargoTypeItem{
//		
//		/**
//		 * 包裹类型（包裹）
//		 */
//		private String cargoTypeName;
//		
		/**
		 * 邮费币种
		 */
		private String currencyName;
		
		/**
		 * 快递类型（顺丰标快、顺丰即日）
		 */
		private String limitTypeName;
		
		/**
		 * 收件时间
		 */
		private Date deliverTime;
		
		/**
		 * 总运费
		 */
		private long totalFreight;
	}

}
