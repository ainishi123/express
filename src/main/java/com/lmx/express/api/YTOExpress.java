package com.lmx.express.api;

import static com.lmx.express.support.yto.Configuration.HOST;
import static com.lmx.express.support.yto.Configuration.ORIGIN;
import static com.lmx.express.support.yto.Configuration.PREDICTION_URL;
import static com.lmx.express.support.yto.Configuration.REFER;

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
import com.lmx.express.support.extractor.YTOResultExtractor;

/**
 * 圆通快递
 * @author 123
 *
 */
public class YTOExpress implements ExpressApi{

	@Override
	public boolean order(ExpressOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PredictionResult predictionPrice(Adress source, Adress target, Item item,Date deliveryTime) {
		
		Map<String, String> formData = new HashMap<>();// forma-data入参
		// from adress
		String province = source.getProvince();
		String proviceCode = source.getProvinceCode();
		String city = source.getCity();
		String cityCode = source.getCityCode();
		if(source.isProvinceLevelCity()) {
			city = source.getArea();
			cityCode = source.getAreaCode();
		}
		String fromLabel = new StringBuilder(province).append(Constants.MIDDLE_LINE)
				.append(city).toString();
		formData.put("fromLabel", fromLabel);
		formData.put("fromProvinceCode", proviceCode);
		formData.put("fromProvinceName",province);
		formData.put("fromCityCode", cityCode);
		formData.put("fromCityName", city);
		// to adress
		String toProvince = target.getProvince();
		String toProviceCode = target.getProvinceCode();
		String toCity = target.getCity();
		String toCityCode = target.getCityCode();
		if(target.isProvinceLevelCity()) {
			toCity = target.getArea();
			toCityCode = target.getAreaCode();
		}
		String toLabel = new StringBuilder(toProvince).append(Constants.MIDDLE_LINE)
				.append(toCity).toString();
		formData.put("toLabel", toLabel);
		formData.put("toProvinceCode", toProviceCode);
		formData.put("toProvinceName", toProvince);
		formData.put("toCityCode", toCityCode);
		formData.put("toCityName", toCity);
		// item
		formData.put("weight", item.getWeight());
		formData.put("length", item.getLength());
		formData.put("width", item.getWidth());
		formData.put("height", item.getHeight());
		// headers
		Map<String, String> headers = new HashMap<>();
		headers.put(HttpHeaders.CONTENT_TYPE,Constants.CONTENT_TYPE_FORM_DATA);
		headers.put(HttpHeaders.HOST, HOST);
		headers.put(HttpHeaders.REFERER, REFER);
		headers.put(HttpHeaders.USER_AGENT, UserAgentPool.randomUserAgent());
		headers.put(Constants.ORIGIN, ORIGIN);
		// 执行请求
		String response = "";
		try {
			response = new Actuator().executor(PREDICTION_URL, formData, headers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return YTOResultExtractor.extractorPredictionResult(response);
	}

}
