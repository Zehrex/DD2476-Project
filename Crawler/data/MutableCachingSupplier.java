3
https://raw.githubusercontent.com/Nuclearfarts/connected-block-textures/master/src/main/java/io/github/nuclearfarts/cbt/util/function/MutableCachingSupplier.java
package io.github.nuclearfarts.cbt.util.function;

import java.util.function.Supplier;

public class MutableCachingSupplier<T> implements Supplier<T> {
	private Supplier<T> supplier;
	private T cache;

	public void set(Supplier<T> newSupplier) {
		supplier = newSupplier;
		cache = null;
	}
	
	@Override
	public T get() {
		return cache == null ? cache = supplier.get() : cache;
	}

}
