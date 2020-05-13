2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/decorate/example/AbstractDecorate.java
package com.wz.structural.decorate.example;

/**
 * @author 隔壁老王
 * @create 2020-04-30 18:07
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//抽象装饰类
public abstract class AbstractDecorate extends AbstractCake {
    private AbstractCake abstractCake;

    public AbstractDecorate(AbstractCake abstractCake) {
        this.abstractCake = abstractCake;
    }

    @Override
    public String description() {
        return abstractCake.description();
    }

    @Override
    public int calculatePrice() {
        return abstractCake.calculatePrice();
    }

    //此抽象类中可以有其他抽象方法，但是其子类必须实现，依据业务情况而定。
    //在这个例子中，可以加一个方法：计算草莓的量和奶油的量。当然，如果加上此方法后，价格的计算业务也应该做更改。
}
