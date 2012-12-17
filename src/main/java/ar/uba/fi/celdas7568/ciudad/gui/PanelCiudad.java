/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.celdas7568.ciudad.gui;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.Ciudad;
import ar.uba.fi.celdas7568.ciudad.OpinionSimple;
import ar.uba.fi.celdas7568.ciudad.Zona;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;
import sun.management.Agent;

/**
 *
 * @author Nico
 */
public class PanelCiudad extends JPanel{
    
    private static Ciudad ciudad;
    private static Agente agente;
    private static final float VALOR_MAXIMO_OPINION = 60f; //Ajustar para sacar más o menos verde
     
    public static void setAgente (Agente a ){PanelCiudad.agente = a;}
    public static void setCiudad (Ciudad c ){PanelCiudad.ciudad = c;}
    
    
    @Override
    public void paintComponent(Graphics g) {
         super.paintComponent(g);
         Iterator<Zona> it = ciudad.iterator();
         int i = 0;
         int j = 0;
         while(it.hasNext()){
             Zona z = it.next();
             OpinionSimple o = (OpinionSimple) agente.getHeuristicaDeDesicion().evaluarZona(z, agente.getPersonalidad());
             float green = (float) (o.getValorOpinion() / VALOR_MAXIMO_OPINION);
             float red   = 1.0f - green;
             Color c = new Color(red,green,0.0f);
             g.setColor(c);
             g.fillRect(j * 30, i * 30, 30, 30);
             j++;
             if (j >= 10) {i++; j = 0;}
         }
         /*
        for (int i=0 ; i<10; i++ )
             for (int j=0 ; j<10; j++ ) {
                 float gr = (float)Math.random();
                 float re = 1-gr;
                Color c = new Color(red,green,0.0f);
                     g.setColor(c);
                      g.fillRect(j * 30, i * 30, 30, 30);
                 
               } 
           */  
      
        
    }
}
