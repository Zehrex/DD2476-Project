21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/enums/PayStatusEnum.java
package com.southwind.phone_store_demo.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    UNPIAD(0,"未支付"),
    PAID(1,"已支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
