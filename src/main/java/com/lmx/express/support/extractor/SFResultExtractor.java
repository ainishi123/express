package com.lmx.express.support.extractor;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lmx.express.model.prediction.PredictionResult;
import com.lmx.express.model.prediction.SFPredictionResult;
import com.lmx.express.model.prediction.SFPredictionResult.CargoTypeItem;

public class SFResultExtractor {

	/**
	 * 提取预测结果
	 * @param response
	 * @return
	 */
	public static PredictionResult extractorPredictionResult(String response) {
		List<CargoTypeItem> cargoTypeItems = JSON.parseArray(response,CargoTypeItem.class);
		SFPredictionResult result = new SFPredictionResult();
		result.setCargoItems(cargoTypeItems);
		return result;
	}
}
