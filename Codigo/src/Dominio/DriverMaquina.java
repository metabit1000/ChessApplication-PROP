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
        Maquina m = new Maquina();
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. PosiblesMovimientos");
            System.out.println("3. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    String fen = "8/PPPR3/PPk5/Pp5r/K5qR/8/4PQ2/1B5B w";
                    p.fenToMatrix(fen);
                    p.printTablero();
                    break;
                case 2:
                    Coordenada c = new Coordenada();
                    String g = "b1";
                    c.stringToCoord(g);
                    m = new Maquina(p.getFicha(c).getColor(),1);
                    Coordenada h = m.getNextMove(p,c);
                    System.out.println(h.getX() + " " + h.getY());
                    String j = h.coordToString();
                    System.out.println(j);
                    Coordenada k = new Coordenada();
                    k.stringToCoord(j);
                    System.out.println(h.getX() + " " + h.getY());
                    break;
                case 3: 
                    estado = 1000;
                    break;
            }
        }
    }
}
