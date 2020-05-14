17
https://raw.githubusercontent.com/geertvos/gs-lang/master/src/main/java/net/geertvos/gvm/ast/Scope.java
package net.geertvos.gvm.ast;

public interface Scope {

	Scope addStatement(Statement statement);

	Statement getStatement(int index);

	int getStatements();
	
}
