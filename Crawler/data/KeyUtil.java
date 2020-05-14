21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/util/KeyUtil.java
package com.southwind.phone_store_demo.util;

import java.util.Random;

public class KeyUtil {
    public static synchronized String createUniqueKey(){
        Random random = new Random();
        Integer key = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(key);
    }
}
