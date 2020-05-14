165
https://raw.githubusercontent.com/JosephTribbianni/JNDI/master/src/main/java/org/su18/asm/echo/ExceptionEcho.java
package org.su18.asm.echo;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ExceptionEcho implements Opcodes {

	public static void insert(String className, MethodVisitor mv) {

		mv.visitTypeInsn(NEW, "java/lang/Exception");
		mv.visitInsn(DUP);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className, "result", "Ljava/lang/String;");
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Exception", "<init>", "(Ljava/lang/String;)V", false);
		mv.visitInsn(ATHROW);
	}

}
