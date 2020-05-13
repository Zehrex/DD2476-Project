1
https://raw.githubusercontent.com/Bekzatiitu/Final_Bekzat_Bekarys_ishs1901/master/Final%20Bekzat%20Bekarys%20ishs1901/src/kenn/shi/Add.java
package kenn.shi;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.MissingFormatArgumentException;

import static java.lang.Integer.parseInt;

public class Add extends Container {
    protected JButton add;
    private JButton back;
    private JComboBox comboBox;
    private JComboBox comboBox1;

    public Add(){
        String[] city = {"Almaty" , "Shymkent" , "Nur-Sultan" , "Taraz"};
        String[] favouriteJobs  ={"Manager" , "Director" , "Administrator" , "Staff"};

        setSize(500 , 500);
        setLayout(null);

        JLabel label = new JLabel("Name:");
        label.setSize(100 , 20);
        label.setLocation(100 , 100);
        add(label);

        JTextField text1 = new JTextField();
        text1.setSize(200 , 20);
        text1.setLocation(200 , 100);
        add(text1);

        JLabel label1 = new JLabel("Surname: ");
        label1.setSize(100 , 20);
        label1.setLocation(100 , 150);
        add(label1);

        JTextField text2 = new JTextField();
        text2.setSize(200 ,20);
        text2.setLocation(200 , 150);
        add(text2);

        JLabel label3 = new JLabel("Age:");
        label3.setSize(100 , 20);
        label3.setLocation(100 , 200);
        add(label3);

        JTextField text4 = new JTextField();
        text4.setSize(200 , 20);
        text4.setLocation(200 , 200);
        add(text4);

        JLabel label4 = new JLabel("City:");
        label4.setSize(100 , 20);
        label4.setLocation(100  ,250);
        add(label4);

        comboBox = new JComboBox(city);
        comboBox.setSize(200 , 20);
        comboBox.setLocation(200 , 250);
        add(comboBox);

        JLabel label5 = new JLabel("Desired:");
        label5.setSize(100 , 20);
        label5.setLocation(100 , 300);
        add(label5);


        comboBox1 = new JComboBox(favouriteJobs);
        comboBox1.setSize(200 , 20);
        comboBox1.setLocation(200 , 300);
        add(comboBox1);

        add = new JButton("ADD");
        add.setSize(100 , 30);
        add.setLocation(100 , 350);
        add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String m = text1.getText();
                String c = (String) text2.getText();
                int y = parseInt(text4.getText());
                String t = (String) comboBox.getSelectedItem();
                String dj = (String) comboBox1.getSelectedItem();

                You staff = new You(null , m , c , y , t , dj);
                Main.addStaff(staff);
                PackageData pd = new PackageData("ADD", staff);
                Main.connect(pd);

            }
        });

        back = new JButton("BACK");
        back.setSize(100 , 30);
        back.setLocation(250 , 350);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.frame.showMenu();
            }
        });


    }


}
