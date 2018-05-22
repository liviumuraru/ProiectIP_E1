package sorting;

import java.awt.Color;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        PanelList frame = new PanelList ();
        //frame.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500,400);
        
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible ( true );
        frame.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );


    }
}
