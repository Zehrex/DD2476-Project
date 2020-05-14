9
https://raw.githubusercontent.com/swesust/covid-19-help-support-bd/master/Covid19Shahajjo/app/src/main/java/com/example/covid19shahajjo/models/ContactsInfo.java
package com.example.covid19shahajjo.models;

public class ContactsInfo {
    public String UNO;
    public String Police;
    public String FireService;
    public String CivilSurgeon;

    @Override
    public String toString(){
        return String.format("UNO: %s\nCivilSurgeon: %s\nPolice: ", UNO,CivilSurgeon,Police);
    }
}
