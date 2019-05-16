/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;



/**
 *
 * @author Jordi
 */
public class Rankingview  extends JFrame{
    
       private Map<String,Double> rank = new HashMap<>();
       CtrlRanking  Cr = new CtrlRanking () ; 
       public Rankingview(){
           super("Mi tabla");
           rank = Cr.getmap(1);
           DefaultTableModel modelo = new DefaultTableModel();
           modelo.addColumn("Nombre");
           modelo.addColumn("Tiempo");
           Iterator iterator=rank.keySet().iterator();
        while(iterator.hasNext()){
            Object key = iterator.next();
          
                Object[]p1={key,rank.get(key)};
                modelo.addRow(p1);
        }
           
           
           
  
           JTable tabla = new JTable(modelo);
            tabla.setEnabled(false);
            tabla.setRowHeight(50);
            tabla.setRowHeight(50, 50);
            tabla.setFont(new java.awt.Font("Tahoma", 0, 20)); 
           JScrollPane scroll = new JScrollPane(tabla);
           tabla.setBounds(12,12,750,750);
           setSize(750,700);
           scroll.setBounds(12,22,750,750);
           add(scroll);
           
                

           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setLayout(null);
           
           setVisible(true);
           
           
       }    
       
       public static void main (String[] a ){
           Rankingview t1 = new Rankingview(); 
          
       }
}
