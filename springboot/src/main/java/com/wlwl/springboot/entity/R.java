package com.wlwl.springboot.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

	private Boolean success;

	private Integer code;

	private String message;

	private Map<String, Object> data = new HashMap<String, Object>();

	private R(){}

	public static R ok(){
		R r = new R();
		r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
		r.setCode(ResultCodeEnum.SUCCESS.getCode());
		r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
		return r;
	}

	public static R error(){
		R r = new R();
		r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
		r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
		r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
		return r;
	}

	public static R setResult(ResultCodeEnum resultCodeEnum){
		R r = new R();
		r.setSuccess(resultCodeEnum.getSuccess());
		r.setCode(resultCodeEnum.getCode());
		r.setMessage(resultCodeEnum.getMessage());
		return r;
	}

	public R success(Boolean success){
		this.setSuccess(success);
		return this;
	}

	public R message(String message){
		this.setMessage(message);
		return this;
	}

	public R code(Integer code){
		this.setCode(code);
		return this;
	}

	public R data(String key, Object value){
		this.data.put(key, value);
		return this;
	}

	public R data(Map<String, Object> map){
		this.setData(map);
		return this;
	}

	public Map<String,Object> toMap(){
		return this.data;
	}
}
