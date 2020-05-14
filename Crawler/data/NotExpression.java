17
https://raw.githubusercontent.com/geertvos/gs-lang/master/src/main/java/net/geertvos/gvm/ast/NotExpression.java
package net.geertvos.gvm.ast;

import net.geertvos.gvm.compiler.GScriptCompiler;
import net.geertvos.gvm.core.GVM;

public class NotExpression extends Expression {

	private Expression argument;
	
	public NotExpression( Expression argument )
	{
		this.argument = argument;
	}
	
	@Override
	public void compile(GScriptCompiler c) {
		argument.compile(c);
		c.code.add(GVM.NOT);
	}

}
