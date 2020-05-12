2
https://raw.githubusercontent.com/radudioane99/Shape-Storm/master/Final.java
package game;

import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Final extends JFrame {
    //Attributes
    JTextArea mesg;
    Container front;
    JButton close;
    //Constructor
        public Final(){
            super("Shape Storm");
            this.setLayout(null);
            this.front= getContentPane();
            this.setLayout(null);
            init();
            Color myBack = new Color(66, 245, 96);
            this.front.setBackground(myBack);
            this.setBounds(250,250,Utils.FI_Size, Utils.FI_Size);
            this.setResizable(false);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
        }

        public void init(){

            //TextArea

            JTextArea mesg= new JTextArea();
            mesg.setText("  Congratulations "+MainWindow.playername+", "+"\n"+ "     You survived "+(MainWindow.end-MainWindow.start)/1000+" seconds");
            mesg.setBounds(20,20,420,60);
            mesg.setEditable(false);
            front.add(mesg);

            //Close

            JButton close= new JButton();
            close.setBounds(165,250,120,100);
            close.setText("Close!");
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
            front.add(close);
        }

}
