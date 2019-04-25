package ClasesExtra;

import Dominio.Problema;
import java.util.Scanner;

/**
 *
 * @author Àlex
 */

/* No he tenido en cuenta todas las funciones de la clase Minimax, sino un ejemplo con un problema para
para ver el correcto funcionamiento del algoritmo y para ir probando en el momento de programarlo. 
Tiene varias funciones "triviales" como evaluationBoard, getPieceValue y getAbsoluteValue que se explican en la clase*/

public class DriverMinimax {
    public static void main (String [] args){
        int estado = 0;
        int fin = 1000; 
        Scanner sc = new Scanner(System.in);
        Minimax prueba = new Minimax();
        int depth;
        char color;
        while (estado != fin) {
            System.out.println("Menu:"); 
            System.out.println("1. Ejemplo de funcionamiento de minimax");
            System.out.println("2. Exit");
            System.out.println("Introduzca un numero: ");
            estado = sc.nextInt();
            if (estado < 0 || estado > 2) System.out.println("Introduzca una opcion valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Ejemplo de funcionamiento");
                    System.out.println("Se introduce problema del enunciado del proyecto");
                    Problema p = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w",2);
                    p.printTablero();
                    System.out.println("Introduzca profundida de busqueda del algoritmo (valores no muy altos, ej 3): ");
                    depth = sc.nextInt();
                    System.out.println("Introduzca el color que tendra la maquina: (n/b)");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    Pair <Coordenada,Coordenada> par = new Pair();
                    if (color == 'n') par = prueba.decisionMinimax(p,depth,false);
                    else if (color == 'b') par =  prueba.decisionMinimax(p,depth,true); 
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    p.printTablero();
                    System.out.print("Coordenadas devueltas por el minimax como mejor movimiento en el problema son: ");
                    System.out.println(par.getKey().coordToString()+" "+par.getValue().coordToString());
                    break;      
                case 2: 
                    estado = 1000;
                    System.out.println("Gracias. Que tenga un buen dia.");
                    break;
            }
        }
    }
}
