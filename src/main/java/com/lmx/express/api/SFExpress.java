package com.lmx.express.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;

import com.lmx.express.model.Adress;
import com.lmx.express.model.ExpressOrder;
import com.lmx.express.model.Item;
import com.lmx.express.model.prediction.PredictionResult;
import com.lmx.express.support.Actuator;
import com.lmx.express.support.UserAgentPool;
import com.lmx.express.support.extractor.SFResultExtractor;
import com.lmx.express.support.sf.Configuration;

/**
 * 顺丰快递
 * @author 123
 *
 */
public class SFExpress implements ExpressApi{
	
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

	@Override
	public boolean order(ExpressOrder order) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public PredictionResult predictionPrice(Adress source, Adress target, Item item,Date deliveryTime) {
		Actuator actuator = new Actuator();
		// headers
		Map<String, String> headers = new HashMap<>();
//		headers.put(HttpHeaders.CONTENT_TYPE,Constants.CONTENT_TYPE_APPLICATION_JSON);
		headers.put(HttpHeaders.HOST, Configuration.HOST);
		headers.put(HttpHeaders.REFERER, Configuration.REFER);
		headers.put(HttpHeaders.USER_AGENT, UserAgentPool.randomUserAgent());
		headers.put("Accept-Encoding", "gzip, deflate, br");
		headers.put("Accept-Language", "zh-CN,zh;q=0.9");
		headers.put("Accept","application/json, text/javascript, */*; q=0.01");
		String sourceAdress = new StringBuilder("A").append(source.getAreaCode()).append("000").toString();
		String targetAdress = new StringBuilder("A").append(target.getAreaCode()).append("000").toString();
		Map<String,String> parameters = new HashMap<>();
		parameters.put("dest", targetAdress);
		parameters.put("origin", sourceAdress);
		parameters.put("weight", item.getWeight());
		parameters.put("region", "cn");
		parameters.put("volume", "0");
		parameters.put("queryType", "2");
		parameters.put("lang", "sc");
		parameters.put("translate", "");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
		parameters.put("time", simpleDateFormat.format(deliveryTime));
		try {
			String response = actuator.executorGet(Configuration.PREDICTION_URL, parameters, headers);
			return SFResultExtractor.extractorPredictionResult(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
