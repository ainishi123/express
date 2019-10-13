package com.lmx.express.api;

import java.util.Date;

import com.lmx.express.model.Adress;
import com.lmx.express.model.ExpressOrder;
import com.lmx.express.model.Item;
import com.lmx.express.model.prediction.PredictionResult;

/**
 * 快递api
 * @author 123
 *
 */
public interface ExpressApi {

	/**
	 * 下单
	 * @param order
	 * @return
	 */
	public boolean order(ExpressOrder order);
	
	/**
	 * 预测价格和到达时间
	 * @param source  	寄件地址
	 * @param target	收件地址
	 * @param item	快递详情
	 * @param deliveryTime	快递时间
	 */
	public PredictionResult predictionPrice(Adress source,Adress target,Item item,Date deliveryTime);
}
