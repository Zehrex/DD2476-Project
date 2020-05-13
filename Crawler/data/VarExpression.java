2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/interpreter/VarExpression.java
package com.wz.behavioral.interpreter;

import java.util.HashMap;

/**
 * @author 隔壁老王
 * @create 2020-05-06 19:46
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//终结符表达式角色：变量的解释器
public class VarExpression implements Expression {

	private String key; // key=a,key=b,key=c

	public VarExpression(String key) {
		this.key = key;
	}

	//interpreter根据变量名称，返回对应的值
	@Override
	public int interpreter(HashMap<String, Integer> var) {
		return var.get(this.key);
	}
}
