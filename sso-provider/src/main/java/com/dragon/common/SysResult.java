package com.dragon.common;

import java.io.Serializable;

/**
 * 自定义响应结构
 *
 */
public class SysResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1535396707343348617L;

	// 响应业务状态
	/*
	 * 200 成功
	 * 201 错误
	 * 400 参数错误
	 */
	private Integer status;
	
	// 响应消息
	private String msg;
	
	// 响应中的数据
	private Object data;
	
	public SysResult(Integer status, String msg, Object data) {
		this.msg = msg;
		this.data = data;
		this.status = status;
	}

	public SysResult(Object data) {
		this.data = data;
	}

	public SysResult() {
		this.status = 200;
		
	}

	public SysResult(Integer status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static SysResult build(Integer status, String msg, Object data) {
		return new SysResult(status, msg, data);
	}
	
	public static SysResult build(Integer status, String msg) {
		return new SysResult(status, msg);
	}
	
	public static SysResult ok(Object data) {
		return new SysResult(data);
	}
	
	public static SysResult ok() {
		return new SysResult();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
