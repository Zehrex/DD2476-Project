21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/dto/OrderDTO.java
package com.southwind.phone_store_demo.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer specsId;
    private Integer phoneQuantity;
}
