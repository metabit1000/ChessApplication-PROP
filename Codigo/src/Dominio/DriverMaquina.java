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
        int fin = 1000; 
        Scanner sc = new Scanner(System.in);
        Maquina prueba = new MaquinaEasy();
        char color;
        String nombre;
        int dif;
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructora");
            System.out.println("2. GetNextMove");
            System.out.println("3. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            if (estado < 0 || estado > 4) System.out.println("Introduzca una opción valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructora");
                    System.out.println("Introduzca un color (n /b): ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println("Introduzca un nombre para la maquina: ");
                    nombre = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca la dificultad con la que quiere la máquina(1 o 2): ");
                    dif = sc.nextInt();
                    if (dif < 1 || dif > 2) {
                        System.out.println("No ha introducido bien la dificultad, vuelva a intentarlo.");
                        break;
                    }
                    if (color == 'n') prueba = new MaquinaEasy(false,nombre, 2);
                    else if (color == 'b') prueba = new MaquinaEasy(true,nombre, 2);
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    System.out.println("Maquina creada correctamente.");
                    break;
                case 2: 
                    System.out.println("Ha elegido: GetNextMove");
                    System.out.println("Se introduce el fen del enunciado para ver algún resultado");
                    Problema p = new Problema(1,"1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w", 2, new Ranking());
                    Pair <Coordenada,Coordenada> move = prueba.getNextMove(p);
                    p.printTablero();
                    try {
                        System.out.println(move.getKey().coordToString()+" "+move.getValue().coordToString());
                    } catch (NullPointerException e) {
                        System.out.println("Debe crear antes la máquina, es necesario un color, vuelva a intentarlo.");
                        break;
                    }
                    break;
                case 3: 
                    estado = 1000;
                    break;
            }
        }
    }
}
