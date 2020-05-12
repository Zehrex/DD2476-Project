3
https://raw.githubusercontent.com/jroutine/jroutine/master/src/main/java/org/coral/jroutine/weave/ClassTransformer.java
package org.coral.jroutine.weave;

/**
 * Bytecode transformer for enhancing class files.
 * 
 * @author lihao
 * @date 2020-05-05
 */
public interface ClassTransformer {

    byte[] transform(byte[] classFile);
}
