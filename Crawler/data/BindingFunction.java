15
https://raw.githubusercontent.com/Florizt/RxMVVM/master/rxmvvmlib/src/main/java/com/rx/rxmvvmlib/binding/command/BindingFunction.java
package com.rx.rxmvvmlib.binding.command;

/**
 * Represents a function with zero arguments.
 *
 * @param <T> the result type
 */
public interface BindingFunction<T> {
    T call();
}
