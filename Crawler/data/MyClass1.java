1
https://raw.githubusercontent.com/falvojr/stackoverflow-61560293/master/src/main/java/com/falvojr/domain/MyClass1.java
package com.falvojr.domain;

public class MyClass1 {

    private String attr1;
    private MyClass2 attr2;

    public MyClass1(String attr1, MyClass2 attr2) {
        super();
        this.attr1 = attr1;
        this.attr2 = attr2;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public MyClass2 getAttr2() {
        return attr2;
    }

    public void setAttr2(MyClass2 attr2) {
        this.attr2 = attr2;
    }

}
