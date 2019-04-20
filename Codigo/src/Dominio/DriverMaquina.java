package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Bishop;
import java.util.Scanner;
import javafx.util.Pair;

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
                    String fen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w";
                    p.fenToMatrix(fen);
                    p.printTablero();
                    break;
                case 2:
                    m = new Maquina(true,1); //juega con blancas
                    Pair<Coordenada,Coordenada> moves = m.getNextMove(p); //prueba del minimax
                    System.out.println(moves.getKey().coordToString()+" "+moves.getValue().coordToString());
                    break;
                case 3: 
                    estado = 1000;
                    break;
            }
        }
    }
}
