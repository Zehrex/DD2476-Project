3
https://raw.githubusercontent.com/Nuclearfarts/connected-block-textures/master/src/main/java/io/github/nuclearfarts/cbt/util/function/ThrowingPredicate.java
package io.github.nuclearfarts.cbt.util.function;

public interface ThrowingPredicate<T, U extends Throwable> {
	boolean test(T t) throws U;
}
