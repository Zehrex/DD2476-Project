1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/Welcome.java
package kenn.shi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends Container {


    public Welcome(){
        setSize(500 , 500);
        setLayout(null);

        JLabel label = new JLabel("Your name: ");
        label.setSize(100 , 20);
        label.setLocation(100 , 100);
        add(label);

        JTextField textField = new JTextField();
        textField.setSize(100 , 20);
        textField.setLocation(200 , 100);
        add(textField);

        JLabel label1 = new JLabel();
        label1.setSize(300  , 20);
        label1.setLocation(100 , 150);
        add(label1);

        JButton ent = new JButton("ENTER");
        ent.setSize(80 , 20);
        ent.setLocation(310 , 100);
        add(ent);
        ent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = textField.getText();
                if (!text.equals("")) {
                    label1.setText("Welcome to WORK SEARCHING Application, " + text + "!");
                    textField.setText("");
                }
                else {
                    label1.setText("Entering name is necessarily!");
                }
            }
        });

        JButton exit = new JButton("EXIT");
        exit.setSize(150 , 30);
        exit.setLocation(100 , 200);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        JButton join = new JButton("JOIN");
        join.setSize(150 , 30);
        join.setLocation(260 , 200);
        add(join);
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.showMenu();
            }
        });







    }
}
