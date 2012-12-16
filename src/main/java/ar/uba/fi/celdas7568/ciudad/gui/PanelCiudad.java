/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.celdas7568.ciudad.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Nico
 */
public class PanelCiudad extends JPanel{
     @Override
    public void paintComponent(Graphics g) {
         super.paintComponent(g);
         for (int i=0 ; i<10; i++ )
             for (int j=0 ; j<10; j++ ) {
                 float gr = (float)Math.random();
                 float re = (float)Math.random();
                 Color c = new Color(re,gr,0.0f);
                 g.setColor(c);
                 
                g.fillRect(j * 30, i * 30, 30, 30);
             }
      
        
    }
}
