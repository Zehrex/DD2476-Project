2
https://raw.githubusercontent.com/pi-181/num-methods-lab2/master/src/main/java/com/demkom58/nmlab2/calculations/EquationSystemSolver.java
package com.demkom58.nmlab2.calculations;

public interface EquationSystemSolver {

    String getName();

    Answer solve();

    boolean makeDominant();

    double[][] getMatrix();

}
