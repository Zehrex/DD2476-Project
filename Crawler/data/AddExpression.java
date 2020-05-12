2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/interpreter/AddExpression.java
package com.wz.behavioral.interpreter;

import java.util.HashMap;

/**
 * @author 隔壁老王
 * @create 2020-05-06 19:46
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//加法解释器
public class AddExpression extends SymbolExpression  {

	public AddExpression(Expression left, Expression right) {
		super(left, right);
	}

	//处理相加
	@Override
	public int interpreter(HashMap<String, Integer> var) {
		//super.left.interpreter(var) ： 返回 left 表达式对应的值 a = 10
		//super.right.interpreter(var): 返回right 表达式对应值 b = 20
		return super.left.interpreter(var) + super.right.interpreter(var);
	}
}
