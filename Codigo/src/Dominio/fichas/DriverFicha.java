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
        char color;
        String res;

        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructora");
            System.out.println("2. GetColor");
            System.out.println("3. SetColor");
            System.out.println("4. GetId");
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
                    if (color == 'n') prueba = new Pawn(false,'p');
                    else if (color == 'b') prueba = new Pawn(true,'P'); 
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
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
                    System.out.println("Introduzca un color(n /b: ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    if (color == 'n') prueba.setColor(false);
                    else if (color == 'b') prueba.setColor(true); 
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    System.out.println("Introducido correctamente, introduzca 2 para comprobarlo");
                    break;
                case 4:
                    System.out.println("Ha elegido: getID");
                    try {
                        char res2 = prueba.getID();
                        System.out.println("El ID de la ficha es: "+res2);
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
