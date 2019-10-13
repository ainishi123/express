package com.lmx.express.support.extractor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lmx.express.model.prediction.PredictionResult;
import com.lmx.express.model.prediction.STOPredictionResult;
import com.lmx.express.support.sto.Configuration;

/**
 * 申通结果提取器
 * @author 123
 *
 */
public class STOResultExtractor {

	
	/**
	 * 提取预测结果
	 * @param response
	 * @return
	 */
	public static PredictionResult extractorPredictionResult(String response) {
		try {
			JSONObject resultJson = JSON.parseObject(response);
			JSONObject dataJson = resultJson.getJSONObject(Configuration.DATA);
			STOPredictionResult result = new STOPredictionResult();
			result.setPrice(dataJson.getBigDecimal(Configuration.PRICE).toString());
			result.setReachTime(dataJson.getDate(Configuration.TIME));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
