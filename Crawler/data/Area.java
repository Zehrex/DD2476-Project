9
https://raw.githubusercontent.com/swesust/covid-19-help-support-bd/master/Covid19Shahajjo/app/src/main/java/com/example/covid19shahajjo/models/Area.java
package com.example.covid19shahajjo.models;

import com.example.covid19shahajjo.utils.Enums;

public class Area {
    public String Id;
    public String Name;

    public Area(){

    }

    @Override
    public String toString(){
        return Id+": "+Name;
    }
}
