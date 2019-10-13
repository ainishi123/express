package com.lmx.delivery;

import java.util.Date;

import org.junit.Test;

import com.lmx.express.api.YundaExpress;
import com.lmx.express.model.Adress;
import com.lmx.express.model.Item;
import com.lmx.express.model.prediction.PredictionResult;

public class YundaTest {
	
	/**
	 * 价格预测
	 * 省市code码 使用国标
	 */
	@Test
	public void testPredictionPrice() {
		Item item = new Item();
		item.setWeight("20"); // kg
		item.setHeight("20");
		item.setLength("20");
		item.setWidth("20");
		Adress source = new Adress();
		source.setProvince("江苏省");
		source.setProvinceCode("320000");
		source.setCity("扬州市");
		source.setCityCode("321000");
//		source.setArea("拱墅区");
		Adress target = new Adress();
		target.setProvince("天津市");
		target.setProvinceCode("120000");
		target.setCityCode("120100");
		target.setCity("天津市");
		target.setArea("河西区");
		target.setAreaCode("120103");
		PredictionResult predictionPrice = new YundaExpress().predictionPrice(source, target, item,new Date());
		System.out.println(predictionPrice);
	}
}
