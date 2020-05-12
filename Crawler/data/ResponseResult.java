2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/utils/ResponseResult.java
package com.chad.wechatdev.commons.utils;

public class ResponseResult {
	/**
	 * 状态码
	 */
	private String code;
	
	/**
	 * 状态码对应信息
	 */
	private String message;
	
	/**
	 * 返回对象
	 */
	public Object vo;


	private Long total;
	
	public ResponseResult(){
		
	}
	
	public ResponseResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResponseResult(String code, String message, Object vo) {
		this.code = code;
		this.message = message;
		this.vo = vo;
	}
	
	/**
	 * 设置状态信息
	 * @param code		状态码
	 * @param message	状态码对应信息
	 */
	public void setResult(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	/**
	 * 设置状态信息
	 * @param code
	 * @param message
	 * @param vo
	 */
	public void setResult(String code, String message, Object vo){
		this.code = code;
		this.message = message;
		this.vo = vo;
	}
	/**
	 * 设置状态信息
	 * @param code
	 * @param message
	 * @param vo
	 */
	public void setResult(String code, String message, Object vo,Long total){
		this.code = code;
		this.message = message;
		this.vo = vo;
		this.total = total;
	}
	
	/**
	 * 返回状态码
	 * 返回00为正常返回，其他为异常类型
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置状态码
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 返回状态信息
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 设置状态信息
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getVo() {
		return vo;
	}

	public void setVo(Object vo) {
		this.vo = vo;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
