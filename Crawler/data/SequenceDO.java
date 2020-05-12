1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/dataobject/SequenceDO.java
package com.loststars.quickbuy.dataobject;

public class SequenceDO {
    
    public static final String ID_NAME_ORDER = "order_info";
    
    private String name;

    private Integer currentValue;

    private Integer step;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}