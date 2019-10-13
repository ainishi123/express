package com.lmx.express.support.extractor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lmx.express.model.prediction.PredictionResult;
import com.lmx.express.model.prediction.YTOPredictionResult;
import com.lmx.express.support.yto.Configuration;

/**
 * 申通结果提取器
 * @author 123
 *
 */
public class YTOResultExtractor {

//	{"Status":true,"ResultValue":"","StatusCode":"OK",
//		"StatusMessage":"查询失败","RecordCount":0,"Data":{"Time":"2019-09-24 17:50:59","Price":23.0}}
	
	/**
	 * 提取预测结果
	 * @param response
	 * @return
	 */
	public static PredictionResult extractorPredictionResult(String response) {
		try {
			JSONObject resultJson = JSON.parseObject(response);
			JSONObject dataJson = resultJson.getJSONObject(Configuration.DATA_FILED);
			YTOPredictionResult result = JSON.toJavaObject(dataJson, YTOPredictionResult.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
