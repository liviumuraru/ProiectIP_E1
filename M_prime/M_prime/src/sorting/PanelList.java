package sorting;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class PanelList extends JFrame {


    private JCheckBox box1 = new JCheckBox("Criterion 1");
    private JCheckBox box2 = new JCheckBox("Criterion 2");
    private JCheckBox box3 = new JCheckBox("Criterion 3");
    private JCheckBox box4 = new JCheckBox("Criterion 4");

    PanelList() {

        JPanel panelUp = new JPanel();
        panelUp.add(new JLabel("this are the results"));
        this.add(panelUp, BorderLayout.NORTH);


        JPanel panelLeft = new JPanel();
        JButton button = new JButton("Sort");
        panelLeft.add(button);
        this.add(panelLeft, BorderLayout.EAST);


        FileTree model = new FileTree(new File("C:\\Users\\Dell\\Desktop"));

        this.add(model, BorderLayout.CENTER);

        button.addActionListener(e -> {

            // Get new tree based on the checkbox criteria

        });


        JPanel panelRight = new JPanel();

        panelRight.setLayout(new GridLayout(5, 1));
        panelRight.add(box1);
        panelRight.add(box2);
        panelRight.add(box3);
        panelRight.add(box4);
        this.add(panelRight, BorderLayout.WEST);




    }

}

