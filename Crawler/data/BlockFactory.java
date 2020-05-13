2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/flyweight/BlockFactory.java
package com.wz.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 隔壁老王
 * @create 2020-05-01 20:28
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//享元工厂角色：生产方块的工厂
public class BlockFactory {
    //用于存储享元对象的享元池。HashMap是线程不安全的。在实际业务应用中要依据业务情况而定。
    public static final Map<String,Block> BLOCK_MAP = new HashMap<>();

    public static Block getBlock(String shape){
        Block block = BLOCK_MAP.get(shape);
        if(block == null){
            block = new BlockImpl(shape);
            BLOCK_MAP.put(shape,block);
        }
        return block;
    }
}
