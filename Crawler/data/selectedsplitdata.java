2
https://raw.githubusercontent.com/AkhilRautela/ledger_system/master/app/src/main/java/com/example/ledgersystem/selectedsplitdata.java
package com.example.ledgersystem;

import java.util.List;
import java.util.Map;

public class selectedsplitdata {
    static String names[]=new String[1000];
    static int i=0;
    selectedsplitdata(List<listforadapter> l){
        for(listforadapter dat:l){
            if(dat.isIsselected()){
                names[i]=dat.getName();
                i++;
            }
        }
    }
}
