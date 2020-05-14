12
https://raw.githubusercontent.com/Pingvin235/bgerp/master/src/ru/bgcrm/dao/expression/ExpressionBasedFunction.java
package ru.bgcrm.dao.expression;

/**
 * Функция JEXL, имеющаая доступ к контексту.
 * Позволяет сократить запись функции.
 */
public class ExpressionBasedFunction {
    protected Expression expression;

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
