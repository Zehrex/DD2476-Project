160
https://raw.githubusercontent.com/c0ny1/java-object-searcher/master/src/test/java/me/gv7/tools/josearcher/entity/BlacklistTest.java
package me.gv7.tools.josearcher.entity;


class BlacklistTest {
    public static void main(String[] args) {
        Blacklist blacklist = new Blacklist.Builder()
                .setField_name("map")
                .setField_type("HashMap")
                .setField_value("test")
                .build();
        System.out.println(blacklist);
    }
}