package Dominio;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class DriverRanking {
    public static void main (String [] args) throws IOException {
        int estado = 0;
        Ranking Prueba = new Ranking();
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. Añadir Usuario Ranking");
            System.out.println("3. Actualizar ranking");
            System.out.println("4. Eliminar");
            System.out.println("5. Exit");
            System.out.println("Introduzca un número: ");
                    
            estado = sc.nextInt();
            if (estado < 0 || estado > 5)  System.out.println("Introduzca un valor entre el 1-5");

            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructor");
                    Prueba = new Ranking (); 
                    System.out.println("Ranking creado correctamente ");
                    break;
                case 2:
                    
                    
                    sc = new Scanner(System.in);
                    String  nombre ; 
                    double tiempo ;                  
                    System.out.println("Ha elegido: Añadir Usuario Ranking");
                    System.out.println("El ranking es  ");
                    Prueba.getclasificacion();
                    System.out.println("Introduzca un nombre ");
                    nombre  = sc.nextLine();
                   
                        if (Prueba.existeix(nombre)){
                            System.out.println("Actualize el tiempo");
                        break;
                        }
                    System.out.println("Introduzca un tiempo  ");
                    tiempo = sc.nextDouble();
                    sc.nextLine();
                    Prueba.setElemento(nombre, tiempo);
                    Prueba.getclasificacion();  
                    break;
                case 3: 
                    System.out.println("Ha elegido: actualizar usuario ");
                    System.out.println("Introduzca un nombre ");
                    nombre  = sc.next();
                    sc.nextLine();
                      if (!Prueba.existeix(nombre)){
                            System.out.println("No existe el usuario en el Ranking");
                        break;
                        }
                    System.out.println("Introduzca un tiempo  ");
                    tiempo = sc.nextDouble();
                    Prueba.setActualizar(nombre, tiempo);
                    Prueba.getclasificacion();
                    break;
                case 4:
                    System.out.println("Ha elegido: Eliminar");
                    System.out.println("Introduzca un nombre ");
                    nombre  = sc.next(); 
                    sc.nextLine();
                     if (!Prueba.existeix(nombre)){
                            System.out.println("No existe el usuario en el Ranking");
                        break;
                        }
                    Prueba.eliminarUsuario(nombre);
                    Prueba.getclasificacion();
                    System.out.println("Se ha obtenido correctamente");
                    break;
                case 5:
                    fin = 5;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;   
            }
        }
    } 
}

