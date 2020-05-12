3
https://raw.githubusercontent.com/RhenaudTheLukark/Lea2C/master/src/fr/ubordeaux/deptinfo/compilation/lea/abstract_syntax/ExprFEATURE.java
package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;

public class ExprFEATURE extends ExprVALUE {

	public ExprFEATURE(Object object, Type type, int line, int column) throws Exception {
		super(object, line, column);
		setType(type);
	}

	public String toString() {
		return "ExprFEATURE(" + getValue().toString() + ":" + getType() + ")";
	}


}
