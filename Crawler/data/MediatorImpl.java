2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/behavioral/mediator/MediatorImpl.java
package com.wz.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 隔壁老王
 * @create 2020-05-08 18:03
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//中介者实现类
public class MediatorImpl implements Mediator {

    private List<Colleague> colleagues = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleague.setMediator(this);
            this.colleagues.add(colleague);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        for(Colleague ob:colleagues) {
            if(!ob.equals(colleague)) {
                ((Colleague)ob).receive();
            }
        }
    }
}
