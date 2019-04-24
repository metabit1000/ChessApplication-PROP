package ClasesExtra;

import java.util.Scanner;

/**
 *
 * @author Àlex
 */

public class DriverMinimax {
    public static void main (String [] args){
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        PairCoordenadas prueba = new PairCoordenadas();
        String cor1,cor2;
        Coordenada pr1 = new Coordenada();
        Coordenada pr2 = new Coordenada();
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Ejemplo de funcionamiento de minimax");
            System.out.println("2. Exit");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            if (estado < 0 || estado > 2) System.out.println("Introduzca una opción valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Ejemplo de funcionamiento");
                    //en proceso...
                    break;      
                case 2: 
                    estado = 1000;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}
