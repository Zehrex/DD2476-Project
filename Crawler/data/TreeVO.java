21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/vo/TreeVO.java
package com.southwind.phone_store_demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeVO {
    private String k = "规格";
    private List<PhoneSpecsVO> v;
    private String k_s = "s1";
}
