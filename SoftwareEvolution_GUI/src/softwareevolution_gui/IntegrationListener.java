/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareevolution_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


public class IntegrationListener implements ActionListener{
    private final SoftwareEvolution_GUI gui;
    
    
    public IntegrationListener(SoftwareEvolution_GUI gui) {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
         if(e.getSource() == gui.getSearchButton()){   
            //Send data to crawler
            
        }
        
    }
    
    
}
