package com.lmx.express.api;

import static com.lmx.express.support.yunda.Configuration.HOST;
import static com.lmx.express.support.yunda.Configuration.ORIGIN;
import static com.lmx.express.support.yunda.Configuration.PREDICTION_URL;
import static com.lmx.express.support.yunda.Configuration.REFER;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;

import com.lmx.express.model.Adress;
import com.lmx.express.model.ExpressOrder;
import com.lmx.express.model.Item;
import com.lmx.express.model.prediction.PredictionResult;
import com.lmx.express.model.prediction.YundaPredictionResult;
import com.lmx.express.support.Actuator;
import com.lmx.express.support.Constants;
import com.lmx.express.support.UserAgentPool;

/**
 * 韵达快递
 * 
 * @author 123
 *
 */
public class YundaExpress implements ExpressApi {

	@Override
	public boolean order(ExpressOrder order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) {
		BigDecimal realCalcWeight = new BigDecimal(8).divide(new BigDecimal(6000),0, RoundingMode.UP);
		System.out.println(realCalcWeight.longValue());
	}

	@Override
	public PredictionResult predictionPrice(Adress source, Adress target, Item item,Date deliveryTime) {
		// forma-data入参
		Map<String, String> formData = new HashMap<>();
		formData.put("act", "Getyunfei");
		formData.put("zl", item.getWeight());
		long calcWeight = Long.valueOf(item.getLength()) 
										* Long.valueOf(item.getWidth()) 
										* Long.valueOf(item.getHeight()); 
//		不足1kg按1kg结算；
		BigDecimal realCalcWeight = new BigDecimal(calcWeight).divide(new BigDecimal(6000),0, RoundingMode.UP);
		
//		体积重量大于实际重量时按体积重量结算，长*宽*高（cm）/6000=体积重量（kg）；
		if(Long.valueOf(item.getWeight()) < realCalcWeight.longValue()) {
			formData.put("zl", String.valueOf(calcWeight));
		}
		// from adress
		String proviceCode = source.getProvinceCode();
		String cityCode = source.getCityCode();
		String areaCode = source.getAreaCode();
		formData.put("sp", proviceCode);
		formData.put("ss", cityCode);
		formData.put("sx",areaCode);
		// to adress
		String toProviceCode = target.getProvinceCode();
		String toCityCode = target.getCityCode();
		String toAreaCode = target.getAreaCode();
		formData.put("mp", toProviceCode);
		formData.put("ms", toCityCode);
		formData.put("mx",toAreaCode);
		// headers
		Map<String, String> headers = new HashMap<>();
		headers.put(HttpHeaders.CONTENT_TYPE,Constants.CONTENT_TYPE_FORM_DATA);
		headers.put(HttpHeaders.HOST, HOST);
		headers.put(HttpHeaders.REFERER, REFER);
		headers.put(HttpHeaders.USER_AGENT, UserAgentPool.randomUserAgent());
		headers.put(Constants.ORIGIN, ORIGIN);
//		报价仅限韵达派送区域，超出韵达派送区域代转EMS，按EMS折扣优惠价格结算
		String response = "";
		try {
			response = new Actuator().executor(PREDICTION_URL, formData, headers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		YundaPredictionResult predictionResult = new YundaPredictionResult();
		predictionResult.setPrice(response);
		return predictionResult;
	}

}
