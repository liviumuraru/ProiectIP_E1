package sorting;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

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

        JButton button = new JButton("<html><font size='5'>Sort</font></html>");
        button.setBackground(Color.GRAY);
        button.setForeground(Color.white);
        button.setPreferredSize(new Dimension(100, 60));        
        this.add(button, BorderLayout.SOUTH);

        /**
         * name of the root file where the folders are
         */

        File sit = new File("C:\\Users\\Dell\\Desktop\\Computer Networks");

        String[] dir = sit.list();
        Vector<String> onlydir = new Vector<>();
        for (String s :
                dir) {
            if (new File(sit.getAbsolutePath() + File.separator + File.separator + s).isDirectory()) {
                onlydir.add(sit.getAbsolutePath() + File.separator + File.separator + s);
            }

        }

        AtomicReference<FileTree> model = new AtomicReference<>();



        model.set(new FileTree(sit, onlydir));
        this.add(model.get(), BorderLayout.CENTER);
        button.addActionListener(e -> {

            // Get new tree based on the checkbox criteria
            this.remove(model.get());

            /**
             * @param rand Vector from Sorting Component
             *
             */

            Vector<String> rand = new Vector<>();

            if (box1.isSelected()) {
                // call method for first sort

                rand.add(onlydir.elementAt(1));
                rand.add(onlydir.elementAt(2));
                rand.add(onlydir.elementAt(3));
                rand.add(onlydir.elementAt(4));
                rand.add(onlydir.elementAt(5));

                model.set(new FileTree(sit, rand));

            }

            if (box2.isSelected()) {
                // call method for second sort
                rand.add(onlydir.elementAt(5));
                rand.add(onlydir.elementAt(4));
                rand.add(onlydir.elementAt(3));
                rand.add(onlydir.elementAt(2));
                rand.add(onlydir.elementAt(1));

                model.set(new FileTree(sit, rand));

            }

            if (box3.isSelected()) {
                // call method for third sort
                rand.add(onlydir.elementAt(4));
                rand.add(onlydir.elementAt(3));
                rand.add(onlydir.elementAt(5));
                rand.add(onlydir.elementAt(1));
                rand.add(onlydir.elementAt(2));

                model.set(new FileTree(sit, rand));
            }
            this.add(model.get(), BorderLayout.CENTER);
            this.revalidate();
        });

        box1.addActionListener(e -> {
            if (box1.isSelected()) {
                box2.setSelected(false);
                box3.setSelected(false);
            }
        });
        box2.addActionListener(e -> {
            if (box2.isSelected()) {
                box1.setSelected(false);
                box3.setSelected(false);
            }
        });
        box3.addActionListener(e -> {
            if (box3.isSelected()) {
                box1.setSelected(false);
                box2.setSelected(false);
            }
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

