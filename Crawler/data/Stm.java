3
https://raw.githubusercontent.com/RhenaudTheLukark/Lea2C/master/src/fr/ubordeaux/deptinfo/compilation/lea/abstract_syntax/Stm.java
package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public abstract class Stm extends Code {

	public Stm(int line, int column) {
		super(line, column);
	}

	@Override
	public void indent() {
	}

	public String generateCode() throws CodeException {
		// Produit un commentaire sommaire pour rappeler
		// l'instruction du langage Léa
		return NL + tab() + "/* " + this.toString() + " */" + NL;
	}

}
