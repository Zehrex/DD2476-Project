1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/Menu.java
package kenn.shi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends Container {
    protected JButton first;
    protected JButton second;
    protected JButton third;
    protected JButton forth;

    public Menu(){
        setSize(500,500);
        setLayout(null);
        first = new JButton("ADD WORKS");
        first.setSize(300 , 30);
        first.setLocation(100 , 100);
        add(first);
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.showAddStudent();
            }
        });

        second = new JButton("LIST FAVOURITES");
        second.setSize(300 , 30);
        second.setLocation(100 , 150);
        add(second);
        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.showListStudents();
                Main.frame.results.refresh();
                Main.frame.results.fillData();
                PackageData pd = new PackageData("LIST");
                Main.connect(pd);
            }
        });

        third = new JButton("RENAME");
        third.setSize(300 , 30);
        third.setLocation(100 , 200);
        add(third);
        third.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.showWelcome();
            }
        });

        forth = new JButton("EXIT");
        forth.setSize(300 , 30);
        forth.setLocation(100 , 250);
        add(forth);
        forth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


    }
}
