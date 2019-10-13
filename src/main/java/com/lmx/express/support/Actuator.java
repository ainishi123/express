package com.lmx.express.support;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Actuator {

	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 执行
	 * 
	 * @param url
	 * @param formData
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public String executorGet(String url, Map<String, String> parameters, Map<String, String> headers)
			throws Exception {
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建uri
		URIBuilder builder = new URIBuilder(url);
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				builder.addParameter(key, parameters.get(key));
			}
		}
		HttpGet httpGet = new HttpGet(builder.build().toString());
		headers.forEach((k, v) -> {
			httpGet.setHeader(k, v);
		});
		// 由客户端执行(发送)Get请求
		try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			return EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 执行
	 * 
	 * @param url
	 * @param formData
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public String executor(String url, Map<String, String> formData, Map<String, String> headers) throws Exception {
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		// 创建Post请求
		HttpPost httpPost = new HttpPost(url);
		Set<Entry<String, String>> entrySet = formData.entrySet();
		List<NameValuePair> paramList = entrySet.stream().map(e -> new BasicNameValuePair(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
		headers.forEach((k, v) -> {
			httpPost.setHeader(k, v);
		});
		httpPost.setEntity(new UrlEncodedFormEntity(paramList, DEFAULT_CHARSET));
		// 由客户端执行(发送)Post请求
		try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			return EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
