package Dominio;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class DriverJugador {
     public static void main (String [] args) throws IOException {
        int estado = 0;
        Jugador  Prueba;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("Introduzca un número: ");
                    
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                  
                    System.out.println("Ha elegido: Constructor");
                  
                   
//                      Prueba = new Jugador (); 
                    System.out.println("Jugador creado correctamente ");
                    break;    
            }

 
        }
     }
}
