package com.lmx.express.support;

import java.util.Arrays;
import java.util.List;

/**
 * 代理池
 * @author 123
 *
 */
public class UserAgentPool {

	private static final List<String> userAgentPool = Arrays.asList(
			"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0",
			"Mozilla/5.0 (X11; CrOS i686 3912.101.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36",
			"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.67 Safari/537.36" 
					);
	
	/**
	 * 随机获取userAgent
	 * @return
	 */
	public static String randomUserAgent() {
		return userAgentPool.get((int) (Math.random() * userAgentPool.size()));
	}
}
