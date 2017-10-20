package neil.api.gateway.http;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	
	/**
	 * 通过key获取json 文本value
	 * 
	 * @param key
	 *            json key
	 * @param str
	 *            json
	 * @return json字符串
	 */
	public static String getValueToJson(String key, String json) {
		JSONObject o = JSONObject.parseObject(json);
		return o.get(key) == null ? null : o.get(key).toString();
	}

}
