package com.lmx.express;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Utils {

	public static void main(String[] args) {
		Utils utils = new Utils();
		utils.doPostTestTwo();
	}
	
	/**
	 * POST---有参测试(对象参数)
	 *
	 * @date 2018年7月13日 下午4:18:50
	 */
	public void doPostTestTwo() {
 
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
 
		// 创建Post请求
		HttpPost httpPost = new HttpPost("https://my.sto.cn/Order/GetPriceMessage");
//		User user = new User();
//		user.setName("潘晓婷");
//		user.setAge(18);
//		user.setGender("女");
//		user.setMotto("姿势要优雅~");
		// 我这里利用阿里的fastjson，将Object转换为json字符串;
		// (需要导入com.alibaba.fastjson.JSON包)
 
		//组织请求参数  
        List<NameValuePair> paramList = new ArrayList <NameValuePair>();  
        paramList.add(new BasicNameValuePair("startCity", "安徽省/芜湖市/鸠江区")); 
        paramList.add(new BasicNameValuePair("endCity", "浙江省/杭州市/拱墅区")); 
        paramList.add(new BasicNameValuePair("weight", "2")); 
        try {  
        	httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        }
 
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		httpPost.setHeader("Host", "my.sto.cn");
		httpPost.setHeader("Origin", "https://my.sto.cn");
		httpPost.setHeader("Referer", "https://my.sto.cn/Order/Index");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
 
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Post请求
			response = httpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
 
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
