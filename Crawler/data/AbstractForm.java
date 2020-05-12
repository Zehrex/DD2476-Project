2
https://raw.githubusercontent.com/pi-181/oop-labs/master/Lab7/src/main/java/com/demkom58/lab7/model/AbstractForm.java
package com.demkom58.lab7.model;

public abstract class AbstractForm implements IWeight {
    protected Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public Wood getWood() {
        return wood;
    }

    public void setWood(Wood wood) {
        this.wood = wood;
    }

    public abstract float volume();

}
