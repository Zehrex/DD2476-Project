17
https://raw.githubusercontent.com/geertvos/gs-lang/master/src/main/java/net/geertvos/gvm/ast/OrExpression.java
package net.geertvos.gvm.ast;

import net.geertvos.gvm.compiler.GScriptCompiler;
import net.geertvos.gvm.core.GVM;

public class OrExpression extends TwoArgumentExpression {

	public OrExpression( Expression rhs , Expression lhs )
	{
		super(lhs,rhs);
	}
	
	@Override
	public void compile(GScriptCompiler c) {
		super.compile(c);
		c.code.add(GVM.OR);
	}

}
