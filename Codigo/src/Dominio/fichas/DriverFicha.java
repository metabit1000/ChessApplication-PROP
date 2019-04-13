package Dominio.fichas;

import ClasesExtra.Coordenada;
import java.util.Scanner;

/**
 *
 * @author Àlex
 */
public class DriverFicha {

    public static void main (String [] args){
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Pawn prueba = new Pawn();
        String color;
        String res;
        Boolean col;
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. GetColor");
            System.out.println("3. SetColor");
            System.out.println("4. GetId");
            System.out.println("5. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructor");
                    System.out.println("Introduzca un color (negro /blanco): ");
                    color = sc.next();
                    sc.nextLine();
                    col = color != "negro";
                    prueba = new Pawn(col,'p'); //da igual el valor
                    System.out.println("Ficha creada correctamente");
                    break;
                case 2:
                    System.out.println("Ha elegido: GetColor");
                    try {
                        if (prueba.getColor()) System.out.println("Blanco");
                        else System.out.println("Negro");
                    } catch (NullPointerException e) {
                        System.out.println("Debe crear antes la ficha, vuelva a intentarlo");
                    }
                    break;
                case 3: 
                    System.out.println("Ha elegido: SetColor");
                    System.out.println("Introduzca un color(negro /blanco: ");
                    color = sc.next();
                    sc.nextLine();
                    col = color != "negro";
                    prueba.setColor(col);
                    System.out.println("Introducido correctamente, introduzca 2 para comprobarlo");
                    break;
                case 4:
                    System.out.println("Ha elegido: getID");
                    try {
                        char res2 = prueba.getID();
                        System.out.println("El ID aleatorio de la ficha es: "+res2);
                    } catch (NullPointerException e) {
                        System.out.println("Debe crear antes la ficha, vuelva a intentarlo");
                    }
                    break;
                case 5: 
                    fin = 5;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}
