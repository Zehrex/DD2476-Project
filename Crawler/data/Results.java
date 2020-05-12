1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/Results.java
package kenn.shi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Results extends Container {
    public static TextArea area;
    protected JButton back;

    public Results(){
        setSize(500 , 500);

        area = new TextArea();
        area.setSize(400 , 300);
        area.setLocation(50 , 50);
        add(area);

        JButton delete = new JButton("DELETE");
        delete.setSize(150 , 30);
        delete.setLocation(130 , 30);
        delete.setLocation(150 , 410);
        add(delete);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                area.setText(null);
            }
        });


        back = new JButton("BACK");
        back.setSize(150 , 30);
        back.setLocation(300 , 410);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.showMenu();
            }
        });
    }

    public void fillData(){
        if (Main.size>0){
            area.append(Main.showResult());
        }
    }

    public void refresh(){
        area.setText(" ");
    }
}
