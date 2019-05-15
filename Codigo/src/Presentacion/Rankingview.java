/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
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
       private final Map<String,Double> rank = new HashMap<>();
 
       public Rankingview(){
           rank = 
           super("Mi tabla");
           DefaultTableModel modelo = new DefaultTableModel();
           modelo.addColumn("Nombre");
           modelo.addColumn("Tiempo");
           while (){
                String nombre ="a";
                double  tiempo = 1;
                String[]p1={nombre,String.valueOf(tiempo)};
                modelo.addRow(p1);
           }
  
           JTable tabla = new JTable(modelo);
           JScrollPane scroll = new JScrollPane(tabla);
           tabla.setBounds(12,22,500,500);
           setSize(750,700);
           scroll.setBounds(12,22,500,500);
           add(scroll);
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setLayout(null);
           setVisible(true);
           
       }    
       
       public static void main (String[] a ){
           Rankingview t1 = new Rankingview(); 
       }
}
