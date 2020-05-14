21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/vo/AddressVO.java
package com.southwind.phone_store_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressVO {
    private Integer id;
    private String areaCode;
    private String name;
    private String tel;
    private String address;
}
