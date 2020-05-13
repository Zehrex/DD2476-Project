2
https://raw.githubusercontent.com/jeanpsilva/osworks/master/src/main/java/com/algaworks/osworks/domain/exception/NegocioException.java
package com.algaworks.osworks.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String message) {
		super(message);
	}

}
