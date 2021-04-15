package com.qunjie.common.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by whs on 2020/12/3.
 */
@Data
public class ApiResult {

	public ApiResult(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;	
		this.list = new ArrayList<Object>();
		this.detail = new HashMap<Long,Object>();
	}
	
	public ApiResult() {
		this(1,null,null);
	}

	/**
	 * 1正常 0 异常
	 */
	private int code;
	/**
	 * 对错误的具体解释
	 */
	private String message;
	/**
	 * 返回的结果包装在value中，value可以是单个对象
	 */
	private Object data;
	
	private List<Object> list;
	
	private Map<Long,Object> detail;

	/**
	 * 一般信息处理
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static ApiResult valueOf(int code, String message, Object data) {
		return new ApiResult(code, message, data);
	}


	public static ApiResult isSuccess(String sResult){
		int code = 0;
		String message = null;
		try {
			JSONObject jsonObject = JSONObject.parseObject(sResult);
			if (jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus").getBoolean("IsSuccess")){
				code = 1;
				message = jsonObject.getJSONObject("Result").getString("Id") != null ?jsonObject.getJSONObject("Result").getString("Id") : "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(code,message,null);
	}
}