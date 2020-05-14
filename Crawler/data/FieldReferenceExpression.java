17
https://raw.githubusercontent.com/geertvos/gs-lang/master/src/main/java/net/geertvos/gvm/ast/FieldReferenceExpression.java
package net.geertvos.gvm.ast;

import net.geertvos.gvm.compiler.Compilable;

public interface FieldReferenceExpression extends Compilable {
	public FieldReferenceExpression setField( Expression e );
}
