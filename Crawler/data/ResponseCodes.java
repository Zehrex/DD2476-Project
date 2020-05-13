2
https://raw.githubusercontent.com/visayang/wechatdev/master/src/main/java/com/chad/wechatdev/commons/utils/ResponseCodes.java
package com.chad.wechatdev.commons.utils;

import org.apache.commons.lang3.StringUtils;


public enum ResponseCodes {
	ErrorCaioneModifyKEY("11","修改购物车商品-获取购物车KEY失败"),
	ErrorCaioneModifyKEY2("11","修改购物车商品-获取redis caioneKey对象失败"),
	ErrorCaioneDeleteKEY("11","删除购物车商品-获取购物车KEY失败"),
	ErrorCaioneDeleteKEY2("11","删除购物车商品-获取redis caioneKey对象失败"),
	ErrorCaioneModifyNumKEY("11","修改购物车人数-获取购物车KEY失败"),
	ErrorCaioneModifyNumKEY2("11","修改购物车商品-获取redis caioneKey对象失败"),


	Success("00", "操作成功"),
	LoginSuccess("01","登录成功！"),
	UnKnownError("11", "服务器发生异常，请重试"),
	Undefined("12", "未定义"),
	EmptyCart("20","购物车中没有需要提交的商品"),
	Unsale("21","该商品已经下架,请选择其他商品进行购买"),
	PromotionChange("003","购物车信息有变化，请到购物车中确定"),
	NotExistedFuncname("004","該功能不存在"),
	NonExistentId("005","受影响行数为0"),
	NoRoleAuthorize("006","該角色無權限"),
	NotAuthorize("007","未授权的操作"),
	CompanyIDORNumberNOFOUND("008","公司代码或者员工编码不正确"),

	/**
	 * NOT NULL 判断
	 * */
	CompnyIDNotNull("009","公司代码不能为空"),
	UserNumberNotNull("010","员工编号不能为空"),
	PassWordNotNull("011","登录密码不能为空"),
	ShopNameNotNull("012","店铺名称不能为空"),
	CompanyNameNotNull("013","公司名称不能为空"),
	UserNameNotNull("014","员工名称不能为空"),
	CompanyIDAlreadyExisted("014","公司代码已存在"),
	/*
	* 盘点 excel
	* */
	InventroyDataNull("3000","盘点数据为空"),
	/**
	 * 用户管理
	 */
	NotExistedUser("1001", "该帐号不存在，请重新登录或注册"),
	WrongPassword("1002", "帐号或密码错误，请重新输入"),
	InvalidAuthkey("1003", "当前登录已超时，请重新登录"),
	ExitedAccount("1004", "该帐号名已被占用"),
	InvalidLogin("1005", "登录状态已失效，请重新登录或注册"),
	ErrorFormatName("1006", "用户名不合法"),
	OrigPasswordWrong("1007", "原密碼錯誤！"),
	OrigPasswordWrong_zh("1007", "原密码错误！"),
	ExistNoWord("1008", "包含不允许注册的词语"),
	ErrorFormatEmail("1009", "Email格式错误"),
	NoRegisEmail("1010", "Email不允许注册"),
	ExistedEmail("1011", "该Email已经被注册"),
	NullPhoneNumber("1012", "手机号码不能为空"),
	WrongVerifyCode("1013", "手机验证码错误"),
	DeleteUser("1015", "该用户已删除"),
	DisableUser("1017", "该用户已被停用"),
	WrongImageVerifyCode("1018","图片验证码错误"),
	InvalidImageVerifyCode("1019","验证码已失效，请重新获取验证码登录！"),
	SaveLocalUser("1021","在本地保存用户信息异常，请重新登录"),
	ParamsError("1027","参数错误"),
	SetNewPassword("1028","请设置新密码"),
	ExistePhoneNumber("1029", "手机号码已经注册！"),
	UserNumberOnly("1030","员工编号已存在！"),
	ExistedName("1031","名称已经存在！"),
	NotExistedTerminal("1032", "未找到对应终端号"),

	/**
	 * 商品管理 10000开始
	 * */
	ProdNumberExisted("10001","商品编号已存在"),
	ProdSpecExisted("10002","商品名称与商品规格 已存在"),
	ProdSpecMaxLength("10003","商品规格字符长度限制为10位"),
	ProdNameExisted("10004","商品名称已经存在"),
	
	/**
	 * 收银管理 模块
	 */
	ExampleNumberExisted("101","范本编号已存在！"),
	ExampleAuthsExisted("102","已授权店铺使用，请取消授权后再试！"),
	
	/**
	 * 促销活动
	 */
	PromoNumberExisted("103","促销编号已存在！"),
	PromoAuthsExisted("104","有店铺授权在用，请取消授权后再删除！"),

	/**
	 *
	 */
	tableidAlreadyExisted("105","店铺桌号已存在"),

	/**
	 *
	 */
	tableidSessionInvalid("106","session过期 请重新扫描桌上二维码"),

	/*
	* 更新接口返回
	* */
	NOUpdateInfo("4000","没用可用的更新信息"),


	orderRefresh("8000","该订单已处理，请刷新状态");
	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回码说明
	 */
	private String message;
	
	/**
	 * 返回对象
	 */
	public Object vo;
	
	
	ResponseCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	ResponseCodes(String code, String message, Object vo) {
		this.code = code;
		this.message = message;
		this.vo = vo;
	}
	
	/**
	 * 通过code获取对应的ResponseCodes
	 * @param code 错误码
	 * @return 响应码对应的ResponseCodes枚举
	 */
	public static ResponseCodes getResponseByCode(String code){
		if(StringUtils.isEmpty(code)){
			throw new NullPointerException("响应编码为空");
		}
		
		for(ResponseCodes responseCode : ResponseCodes.values()){
			if(responseCode.getCode().equals(code)){
				return responseCode;
			}
		}
		
		throw new IllegalArgumentException("未能找到匹配的ResponseCodes:" + code);
	}
	
	/**
	 * 获取响应编码
	 * @return
	 */
	public String getCode(){
		return this.code;
	}
	
	/**
	 * 获取编码对应消息
	 * @return
	 */
	public String getMessage(){
		return this.message;
	}
	
	/**
	 * 获取返回对象
	 * @return
	 */
	public Object getVo() {
		return vo;
	}
}
