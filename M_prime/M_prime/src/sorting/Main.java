package sorting;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PanelList frame = new PanelList ();
        frame.setVisible ( true );
        //frame.setBackground(Color.LIGHT_GRAY);
        frame.setSize(820,680);
        
        frame.setLocationRelativeTo(null);
        //frame.pack();

        frame.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );


    }
}
