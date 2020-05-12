2
https://raw.githubusercontent.com/AkhilRautela/ledger_system/master/app/src/main/java/com/example/ledgersystem/money_split_data.java
package com.example.ledgersystem;

public class money_split_data {
    String name;
    String money;

    money_split_data(String name,String money){
        this.name=name;
        this.money=money;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
