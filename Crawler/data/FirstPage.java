2
https://raw.githubusercontent.com/radudioane99/Shape-Storm/master/FirstPage.java
package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends JFrame {
    //Declare the necessary fields
    JTextField reds;
    JTextField blues;
    JTextField name;
    JButton play;
    JLabel hint;
    JTextField recomand;
    JLabel reds_dis;
    JLabel blues_dis;
    JLabel name_dis;
    Container front;
    public static int nrreds;
    public static int nrblues;


    //Constructor

    public FirstPage(){
        super("Shape Storm");
        this.setLayout(null);
        this.front= getContentPane();
        this.setLayout(null);
        init();
        Color myBack = new Color(66, 245, 96);
        this.front.setBackground(myBack);
        this.setSize(Utils.FP_SIZE, Utils.FP_SIZE);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void init(){
         //Reds
         JLabel reds_dis = new JLabel();
         reds_dis.setBounds(70,40,200,40);
         reds_dis.setBackground(Color.WHITE);
         reds_dis.setForeground(Color.RED);
         reds_dis.setText("Red enemies (the small ones)");
         front.add(reds_dis);

         JTextField reds= new JTextField();
         reds.setBounds(70,110,70,40);
         reds.setBackground(Color.WHITE);
         reds.setForeground(Color.RED);

         front.add(reds);

         //Blues
         JLabel blues_dis = new JLabel();
         blues_dis.setBounds(400,40,200,40);
         blues_dis.setBackground(Color.WHITE);
         blues_dis.setForeground(Color.BLUE);
         blues_dis.setText("Blue enemies (the big ones)");
         front.add(blues_dis);

         JTextField blues= new JTextField();
         blues.setBounds(400,110,70,40);
         blues.setBackground(Color.WHITE);
         blues.setForeground(Color.BLUE);

         front.add(blues);

         //Hint
        JLabel hint = new JLabel();
        hint.setBackground(Color.WHITE);
        hint.setForeground(Color.BLACK);
        hint.setText("HINT!! : If you have no escape try to run fast through an object!");
        hint.setBounds(20, 600, 600, 40);
        front.add(hint);

        //Recomand


        JTextField recomand = new JTextField();
        recomand.setBounds(70, 170, 300, 40);
        recomand.setBackground(Color.WHITE);
        recomand.setForeground(Color.BLACK);
        recomand.setEditable(false);
        recomand.setText("Recommended: REDS: 8, Blues: 6");
        front.add(recomand);

        //Name

        JLabel name_dis = new JLabel();
        name_dis.setBounds(75,230,200,40);
        name_dis.setBackground(Color.WHITE);
        name_dis.setForeground(Color.BLACK);
        name_dis.setText("Enter your name:");
        front.add(name_dis);

        JTextField name= new JTextField();
        name.setBounds(70,280,250,40);
        name.setBackground(Color.WHITE);
        name.setForeground(Color.BLACK);
        front.add(name);


        //Play

        JButton play = new JButton();
        play.setBounds(400, 400, 200, 100);
        play.setText("PLAY!");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (reds.getText().equals("") || blues.getText().equals("") || name.getText().equals("")) {
                    JOptionPane.showMessageDialog(front, "Insert both number of enemies(Reds and Blues) and your name! ", "Enter Both Numbers and your name", JOptionPane.ERROR_MESSAGE);
                    // Message box -> error -> forget to write a number (reds or blues) or name
                    return;
                } else {
                    //Get the name,numbers of enemies and start the game
                    FirstPage.nrreds = Integer.parseInt(reds.getText());
                    FirstPage.nrblues = Integer.parseInt(blues.getText());
                    MainWindow.playername=name.getText();
                    //Minimum one enemy of each type
                    if (nrreds == 0) nrreds++;
                    if (nrblues == 0) nrblues++;
                    //Hide first page
                    dispose();
                    new Thread(() -> {
                        Character character = new Character();
                        character.setSize(Utils.CHARACTER_SIZE, Utils.CHARACTER_SIZE);

                        List<Enemy> enemies = new ArrayList<>();
                        for (int i = 0; i < nrreds; i++) {
                            Enemy enemy = new Enemy();
                            enemy.setSize(Utils.CHARACTER_SIZE, Utils.CHARACTER_SIZE);
                            enemies.add(enemy);
                        }

                        List<Enemy2> enemies2 = new ArrayList<>();
                        for (int i = 0; i < nrblues; i++) {
                            Enemy2 enemy2 = new Enemy2();
                            enemy2.setSize(Utils.ENEMEY2_SIZE, Utils.ENEMEY2_SIZE);
                            enemies2.add(enemy2);
                        }
                        // Start the game
                        MainWindow application = new MainWindow(character, enemies, enemies2);
                        application.setVisible(true);

                        Controller control = new Controller(enemies, character, enemies2);
                        control.start();

                        Controller2 control2 = new Controller2(enemies2, character);
                        control2.start();
                        //Set timer
                         MainWindow.start = System.currentTimeMillis();
                    }).start();

                }

            }


        });
        front.add(play);
    }
}
