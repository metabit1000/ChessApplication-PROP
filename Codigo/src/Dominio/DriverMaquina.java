package Dominio;

import ClasesExtra.*;
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
                    String fen = "8/8/8/R4R2/8/2pkp3/8/8 w";
                    p.fenToMatrix(fen);
                    p.printTablero();
                    break;
                case 2:
                    m = new Maquina(true,"m1",1); //juega con blancas
                    Pair move = m.getNextMove(p); //prueba del minimax
                    System.out.println(move.getKey().coordToString()+" "+move.getValue().coordToString());
                    break;
                case 3: 
                    estado = 1000;
                    break;
            }
        }
    }
}
