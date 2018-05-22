package sorting;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class PanelList extends JFrame {

    private JCheckBox box1 = new JCheckBox("Number of Contributor Followers");
    private JCheckBox box2 = new JCheckBox("Number of Forks");
    private JCheckBox box3 = new JCheckBox("Number of Releases");
//    private JCheckBox box4 = new JCheckBox("Criterion 4");

    PanelList() {
    	
        JPanel panelUp = new JPanel();
        panelUp.add(new JLabel("<html><font color='white'>These are the results</font></html>"));
        this.add(panelUp, BorderLayout.NORTH);
        panelUp.setBackground(Color.GRAY);
        //this.setSize


        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Color.LIGHT_GRAY);
        JButton button = new JButton("<html><font color='black'>Sort</font></html>");
        button.setBackground(Color.white);
        
        panelLeft.add(button);
        this.add(panelLeft, BorderLayout.EAST);


        FileTree model = new FileTree(new File("M_Prime"));

        this.add(model, BorderLayout.CENTER);

        button.addActionListener(e -> {

            // Get new tree based on the checkbox criteria

        });

        box1.setBackground(Color.LIGHT_GRAY);
        box2.setBackground(Color.LIGHT_GRAY);
        box3.setBackground(Color.LIGHT_GRAY);
//        box4.setBackground(Color.LIGHT_GRAY);
        JPanel panelRight = new JPanel();

        panelRight.setLayout(new GridLayout(3, 1));
        panelRight.add(box1);
        panelRight.add(box2);
        panelRight.add(box3);
//        panelRight.add(box4);
        this.add(panelRight, BorderLayout.WEST);

    }

}

