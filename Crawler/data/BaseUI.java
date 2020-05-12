2
https://raw.githubusercontent.com/Collinsbrefo123/DATA-VISUALIZATION/master/src/BaseUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseUI extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    private Container con;
    private JTable table1 = new JTable(26,26);

    //menu
    private JMenuBar mainBar = new JMenuBar();
    //menu list
    private JMenu file = new JMenu("File");
    private JMenu home = new JMenu("Home");
    private JMenu insert1 = new JMenu("Insert");
    private JMenu graph = new JMenu("Graph");
    private JMenu chart = new JMenu("Chart");

    //items under File
    private JMenuItem newFile = new JMenuItem("New");
    private JMenuItem open = new JMenuItem("Open");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem saveAs = new JMenuItem("Save As");
    private JMenuItem exit = new JMenuItem("Exit");

    //items under Insert
    private JMenuItem pieChart = new JMenuItem("Pie Chart");
    private JMenuItem doughnutC = new JMenuItem("Doughnut Chart");
    private JMenuItem lineGraph = new JMenuItem("Line Graph ");
    private JMenuItem histogram = new JMenuItem("Histogram ");


    public BaseUI(){
        super("Data Visualization App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        con = getContentPane();
        con.add(scrollPane);
        panel.add(table1);
        

        setJMenuBar(mainBar); //add menu bar
        file.setMnemonic('w');
        //add tabs
        mainBar.add(file);
        mainBar.add(home);
        mainBar.add(insert1);

        //File Menu
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(exit);

        //Insert Menu
        insert1.add(graph);
        graph.setIcon(new ImageIcon("yllw.jpg"));
        insert1.add(chart);
        chart.add(pieChart);
        chart.add(doughnutC);
        graph.add(lineGraph);
        graph.add(histogram);


        //action listeners
        exit.addActionListener(this);
        open.addActionListener(this);
        newFile.addActionListener(this);
        saveAs.addActionListener(this);
        save.addActionListener(this);



    }
    public static void main(String []args){
        final int WIDTH = 500;
        final int HEIGHT = 500;
        BaseUI frame = new BaseUI();
        frame.setBounds(850,100,WIDTH, HEIGHT);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Container con = getContentPane();

        if (source == exit)
            System.exit(0);

    }
}
