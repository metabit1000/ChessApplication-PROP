/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Bishop;
import java.util.Scanner;

/**
 *
 * @author Àlex
 */
public class DriverMaquina {
     public static void main (String [] args) { 
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        char color;
        Problema p = new Problema();
        String coordenada;
        Maquina m = new Maquina(true,1);
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. PosiblesMovimientos");
            System.out.println("3. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    String fen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B b";
                    p.fenToMatrix(fen);
                    p.printTablero();
                    break;
                case 2:
                    Coordenada c = new Coordenada();
                    String g = "a1";
                    c.stringToCoord(g);
                    Coordenada h = m.getNextMove(p,c);
                    System.out.println(h.getX() +" "+h.getY());
                    break;
                case 3: 
                    estado = 1000;
                    break;
            }
        }
    }
}
