21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/entity/PhoneCategory.java
package com.southwind.phone_store_demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PhoneCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
