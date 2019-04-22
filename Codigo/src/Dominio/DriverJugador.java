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
        Jugador prueba = new Usuario();
        char color;
        String nombre;
        int fin = 1000;
        Scanner sc = new Scanner(System.in);
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructora");
            System.out.println("2. GetNombre");
            System.out.println("3. GetColor");
            System.out.println("4. Salir");
            System.out.println("Introduzca un número: ");    
            estado = sc.nextInt();
            if (estado < 0 || estado > 4) System.out.println("Introduzca una opción valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructora");
                    System.out.println("Introduzca un color (n /b): ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println("Introduzca un nombre para el jugador: ");
                    nombre = sc.next();
                    sc.nextLine();
                    if (color == 'n') prueba = new Usuario(false,nombre);
                    else if (color == 'b') prueba = new Usuario(true,nombre);
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    System.out.println("Jugador creado correctamente");
                    break;
                case 2: 
                    System.out.println("Ha elegido: GetNombre");
                    if (prueba.getNombre() != null)System.out.println(prueba.getNombre());
                    else System.out.println("Debe crear antes el jugador, vuelva a intentarlo");
                    break;
                case 3:
                    System.out.println("Ha elegido: GetColor");
                    try {
                        if (prueba.getColor()) System.out.println("Blanco");
                        else System.out.println("Negro");
                    } catch (NullPointerException e) {
                        System.out.println("Debe crear antes el jugador, vuelva a intentarlo");
                    }
                    break;
                case 4:
                    estado = 1000;
                    break;  
            }
        }
     }
}
