3
https://raw.githubusercontent.com/RhenaudTheLukark/Lea2C/master/src/fr/ubordeaux/deptinfo/compilation/lea/abstract_syntax/CodeException.java
package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public class CodeException extends Exception {

	private static final long serialVersionUID = 1L;

	public CodeException(String msg) {
		super("--- Erreur lors de la génération: " + msg);
	}

}
