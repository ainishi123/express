package com.lmx.express.api;

import static com.lmx.express.support.sto.Configuration.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;

import com.lmx.express.model.Adress;
import com.lmx.express.model.ExpressOrder;
import com.lmx.express.model.Item;
import com.lmx.express.model.prediction.PredictionResult;
import com.lmx.express.support.Actuator;
import com.lmx.express.support.Constants;
import com.lmx.express.support.UserAgentPool;
import com.lmx.express.support.extractor.STOResultExtractor;


/**
 * 申通快递
 * 
 * @author 123
 *
 */
public class STOExpress implements ExpressApi {

	public boolean order(ExpressOrder order) {
		return false;
	}

	public PredictionResult predictionPrice(Adress source, Adress target, Item item,Date deliveryTime) {
		Actuator actuator = new Actuator();
		Map<String, String> formData = new HashMap<>();
		StringBuilder start = new StringBuilder().append(source.getProvince()).append(Constants.SLANT)
													.append(source.getCity()).append(Constants.SLANT)
													.append(source.getArea());
		// forma-data入参
		formData.put(START_CITY_FIELD, start.toString());
		StringBuilder end = new StringBuilder().append(source.getProvince()).append(Constants.SLANT)
				.append(source.getCity()).append(Constants.SLANT)
				.append(source.getArea());
		formData.put(END_CITY_FIELD, end.toString());
		formData.put(ITEM_WEIGHT_FIELD, item.getWeight());
		// headers
		Map<String, String> headers = new HashMap<>();
		headers.put(HttpHeaders.CONTENT_TYPE,Constants.CONTENT_TYPE_FORM_DATA);
		headers.put(HttpHeaders.HOST, HOST_MY);
		headers.put(HttpHeaders.REFERER, REFER_MY);
		headers.put(HttpHeaders.USER_AGENT, UserAgentPool.randomUserAgent());
		headers.put(Constants.ORIGIN, ORIGIN_MY);
		// 执行
		String response = "";
		try {
			response = actuator.executor(PREDICTION_URL, formData, headers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return STOResultExtractor.extractorPredictionResult(response);
	}

}
