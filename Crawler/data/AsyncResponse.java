14
https://raw.githubusercontent.com/orange451/JRest/master/src/io/jrest/AsyncResponse.java
package io.jrest;

@FunctionalInterface
public interface AsyncResponse<Q> {
	public void response(ResponseEntity<Q> response);
}
