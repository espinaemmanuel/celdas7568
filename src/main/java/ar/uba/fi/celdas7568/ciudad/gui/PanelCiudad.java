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
import javax.swing.JToolTip;
import sun.management.Agent;

/**
 *
 * @author Nico
 */
public class PanelCiudad extends JPanel{
    private static String estado = "stop";
    private static Ciudad ciudad;
    private static Agente agente;
   
    private static final float VALOR_MAXIMO_OPINION = 16f; //Ajustar para sacar más o menos verde
     
    public static void setAgente (Agente a ){PanelCiudad.agente = a;}
    public static void setCiudad (Ciudad c ){PanelCiudad.ciudad = c;}
    public static void setEstado (String e ){PanelCiudad.estado = e;}
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
         
         if (estado == "stop"){
        	 for (int i=0 ; i<10; i++ )
                 for (int j=0 ; j<10; j++ ) {
                     float gr = (float)Math.random();
                     float re = 1-gr;
                    Color c = new Color(0.5f,0.5f,0.5f);
                         g.setColor(c);
                         g.fillRect(j * 30, i * 30, 30, 30);
                         String chars = "Simulación Detenida";
                         g.setColor(Color.black);
                         g.drawChars(chars.toCharArray(), 0 , chars.length(), 100, 100);
                 }
         	}
        if (estado == "step"){
            	 for (int i=0 ; i<10; i++ )
                     for (int j=0 ; j<10; j++ ) {
                         float gr = (float)Math.random();
                         float re = 1-gr;
                        Color c = new Color(0.5f,0.5f,0.5f);
                             g.setColor(c);
                             g.fillRect(j * 30, i * 30, 30, 30);
             }
        }
        	 
        	 
         if (estado == "runAll"){
	         Iterator<Zona> it = ciudad.iterator();
                 int totZonas = ciudad.getCantZonas();
                 int sqrt = (int) Math.sqrt(totZonas);
                 int dimension = (int) (300 / (sqrt + 1));
	         int i = 0;
	         int j = 0;
	         while(it.hasNext()){
	             Zona z = it.next();
                    if (z != null) {
                        OpinionSimple o = (OpinionSimple) agente.getHeuristicaDeDesicion().evaluarZona(z, agente.getPersonalidad());
                        float green = (float) (o.getValorOpinion() / VALOR_MAXIMO_OPINION);
                       
                        if (green > 1.0f) green = 1.0f;
                        if (green < 0.0f) green = 0.0f;
                        
                        float red   = 1.0f - green;
                        Color c = new Color(red,green,0.0f);
                        g.setColor(c);
                        g.fillRect(j * dimension, i * dimension, dimension, dimension);
                        j++;
                        if (j >  sqrt ) {i++; j = 0;}
                     }
                     
	         }
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
    @Override
     public boolean contains(int x, int y) {
        if (ciudad != null) {
            int totZonas = ciudad.getCantZonas(); 
            int sqrt = (int) Math.sqrt(totZonas);
            int fila = y / (300 / (sqrt + 1));           
            int col = x / (300 / (sqrt + 1));   
            int zona = fila * (sqrt + 1) +  col;
            if (zona < totZonas)
                setToolTipText("Row: " + fila + " - Col: " + col + " / Zona: " + zona );
            
        }
        return super.contains(x, y);
      }
}
