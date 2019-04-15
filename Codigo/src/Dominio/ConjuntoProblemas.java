package Dominio;
import ClasesExtra.Coordenada;

import java.util.*;

/**
 *
 * @author joan
 */
public class ConjuntoProblemas {

    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Problema prueba = new Problema();

        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Mover ficha");
            System.out.println("3. MatrixToFen");
            System.out.println("4. Exit");
            
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    String fen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B b";
                    prueba.fenToMatrix(fen);
                    prueba.printTablero();
                    break;
                case 2:
                    String c1;
                    String c2;
                    Coordenada c;
                    System.out.println("Ha elegido: Mover ficha");
                    System.out.println("Introduzca un Coordenada de origen: ");
                    c1 = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada de destino: ");
                    c2 = sc.next();
                    sc.nextLine();
                    prueba.moveFicha(c1,c2);
                    prueba.printTablero();
                    break;
                case 3: 
                    String lol = prueba.matrixToFen();
                    prueba.fenToMatrix(lol);
                    prueba.printTablero();
                    break;   
                case 4: 
                    fin = 4;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}

