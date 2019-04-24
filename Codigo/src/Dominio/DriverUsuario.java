package Dominio;

import ClasesExtra.*;
import java.util.Scanner;

/**
 *
 * @author Joan
 */
public class DriverUsuario {
    public static void main (String [] args) {
        int estado = 0;
        Usuario prueba = new Usuario();
        char color;
        String nombre,password;
        int fin = 1000;
        Scanner sc = new Scanner(System.in);
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructora");
            System.out.println("2. GetPassword");
            System.out.println("3. SetPassword");
            System.out.println("4. GetNextMove");
            System.out.println("5. Salir");
            System.out.println("Introduzca un número: ");    
            estado = sc.nextInt();
            if (estado < 0 || estado > 5) System.out.println("Introduzca una opción valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructora");
                    System.out.println("Introduzca un color (n /b): ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    System.out.println("Introduzca un nombre para el usuario: ");
                    nombre = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca un password para el usuario: ");
                    password = sc.next();
                    sc.nextLine();
                    if (color == 'n') prueba = new Usuario(false,nombre,password);
                    else if (color == 'b') prueba = new Usuario(true,nombre,password);
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    System.out.println("Usuario creado correctamente");
                    break;
                case 2: 
                    System.out.println("Ha elegido: GetPassword");
                    if (prueba.getPassword() != null) System.out.println(prueba.getPassword());
                    else System.out.println("Debe crear antes el usuario, vuelva a intentarlo");
                    break;
                case 3:
                    System.out.println("Ha elegido: SetPassword");
                    System.out.println("Introduzca un password cualquiera: "); 
                    password = sc.next();
                    sc.nextLine();
                    prueba.setPassword(password);
                    break;
                case 4: 
                    System.out.println("Ha elegido: GetNextMove");
                    Problema p = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w", 2);
                    System.out.println("Puede introducir cualquier coordenada: ");
                    Pair <Coordenada,Coordenada> move = prueba.getNextMove(p);
                    System.out.println("Ha introducido: "+move.getKey().coordToString()+" "+move.getValue().coordToString());
                    break;
                case 5:
                    estado = 1000;
                    break;  
            }
        }
     }
}
