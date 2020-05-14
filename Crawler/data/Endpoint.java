14
https://raw.githubusercontent.com/orange451/JRest/master/src/io/jrest/EndPoint.java
package io.jrest;

@FunctionalInterface
public interface EndPoint<Q,P> {
	public ResponseEntity<Q> run(HttpRequest<P> request);
}
