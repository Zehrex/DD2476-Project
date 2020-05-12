12
https://raw.githubusercontent.com/Col-E/SimAnalyzer/master/src/main/java/me/coley/analysis/exception/TypeMismatchKind.java
package me.coley.analysis.exception;

/**
 * Situation where type mismatch occurred.
 *
 * @author Matt
 */
public enum TypeMismatchKind {
	GETFIELD,
	PUTSTATIC,
	INVOKE_HOST_NULL,
	INVOKE_HOST_TYPE,
	INVOKE_ARG_TYPE,
	RETURN
}