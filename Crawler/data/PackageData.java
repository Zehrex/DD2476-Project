1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/PackageData.java
package kenn.shi;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private You you;
    private ArrayList<You> yous;

    public PackageData(String operationType, You you) {
        this.operationType = operationType;
        this.you = you;
    }

    public PackageData(String operationType) {
        this.operationType = operationType;
    }

    public PackageData(ArrayList<You> yous) {
        this.yous = yous;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public You getYou() {
        return you;
    }

    public void setYou(You you) {
        this.you = you;
    }

    public ArrayList<You> getYous() {
        return yous;
    }

    public void setYous(ArrayList<You> yous) {
        this.yous = yous;
    }
}
