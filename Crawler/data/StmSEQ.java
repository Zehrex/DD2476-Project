3
https://raw.githubusercontent.com/RhenaudTheLukark/Lea2C/master/src/fr/ubordeaux/deptinfo/compilation/lea/abstract_syntax/StmSEQ.java
package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmSEQ extends StmBinary {

	public StmSEQ(Stm left, Stm right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	public String generateCode() throws CodeException {
		return getLeft().generateCode() + getRight().generateCode();
	}

	@Override
	public void checkType() throws TypeException {
		getLeft().checkType();
		getRight().checkType();
	}

}
