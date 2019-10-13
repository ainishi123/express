package com.lmx.delivery;

import java.util.Date;

import org.junit.Test;

import com.lmx.express.api.STOExpress;
import com.lmx.express.model.Adress;
import com.lmx.express.model.Item;
import com.lmx.express.model.prediction.PredictionResult;

public class StoTest {
	

	/**
	 * 预测价格
	 * 省市code码 使用国标
	 */
	@Test
	public void testPredictionPrice() {
		Item item = new Item();
		item.setWeight("20"); // kg
		Adress source = new Adress();
		source.setProvince("浙江省");
		source.setCity("杭州市");
		source.setArea("拱墅区");
		Adress target = new Adress();
		target.setProvince("天津市");
		target.setCity("天津市");
		target.setArea("河西区");
		PredictionResult predictionPrice = new STOExpress().predictionPrice(source, target, item,new Date());
		System.out.println(predictionPrice);
	}
}
