1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/bean/contract/ClearingBean.java
package com.hjq.demo.bean.contract;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class ClearingBean {
    public ClearingBean() {
    }

    public ClearingBean(int num, String title, String remark, String value, String change) {
        this.num = num;
        this.title = title;
        this.remark = remark;
        this.value = value;
        this.change = change;
    }

    private int num;
    private String title;
    private String remark;
    private String value;
    private String change;
}
