3
https://raw.githubusercontent.com/RhenaudTheLukark/Lea2C/master/src/fr/ubordeaux/deptinfo/compilation/lea/environment/EnvironmentException.java
package fr.ubordeaux.deptinfo.compilation.lea.environment;

public class EnvironmentException extends Exception {

	public EnvironmentException(String msg) {
		super("--- Erreur sur l'environnement " + msg);
	}

}
