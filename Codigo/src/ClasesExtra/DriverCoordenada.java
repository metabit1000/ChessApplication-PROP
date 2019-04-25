package ClasesExtra;

import Dominio.Problema;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class DriverCoordenada {
    public static void main (String [] args){
        int estado = 0;
        int fin = 1000; //por poner un numero..
        Scanner sc = new Scanner(System.in);
        Coordenada prueba = new Coordenada();
        int x,y;
        String coordenada;
        while (estado != fin) {
            System.out.println("Menu:");
            System.out.println("1. Constructora");
            System.out.println("2. GetX");
            System.out.println("3. SetX");
            System.out.println("4. GetY");
            System.out.println("5. SetY");
            System.out.println("6. StringToCoordenada");
            System.out.println("7. CoordenadaToString");
            System.out.println("8. Exit");
            System.out.println("Introduzca un numero: ");
            estado = sc.nextInt();
            if (estado < 0 || estado > 8) System.out.println("Introduzca una opcion valida");
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructora");
                    System.out.println("Introduzca una coordenada X: ");
                    x = sc.nextInt();
                    System.out.println("Introduzca una coordenada Y: ");
                    y = sc.nextInt();
                    prueba = new Coordenada(x,y);
                    System.out.println("Coordenada creada correctamente.");
                    break;
                case 2:
                    System.out.println("Ha elegido: GetX");
                    System.out.println("La coordenada X es: "+ prueba.getX());
                    System.out.println("Si la coordenada es 0, puede que no haya creado la coordenada, si es el caso vuelva a intentarlo");
                    break;
                case 3: 
                    System.out.println("Ha elegido: SetX");
                    System.out.println("Introduzca una coordenada X: ");
                    x = sc.nextInt();
                    prueba.setX(x);
                    System.out.println("Coordenada X introducida correctamente.");
                    break;
                case 4:
                    System.out.println("Ha elegido: getY");
                    System.out.println("La coordenada Y es: "+ prueba.getY());
                    System.out.println("Si la coordenada es 0, puede que no haya creado la coordenada, si es el caso vuelva a intentarlo");
                    break;
                case 5: 
                    System.out.println("Ha elegido: setY");
                    System.out.println("Introduzca una coordenada Y: ");
                    y = sc.nextInt();
                    prueba.setY(y);
                    System.out.println("Coordenada Y introducida correctamente.");
                    break;
                case 6:
                    System.out.println("Ha elegido: StringToCoordenada");
                    System.out.println("Introduzca un coordenada del tablero: ");
                    Problema p = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w", 2);
                    System.out.println("Se introduce un tablero aleatorio");
                    p.printTablero();
                    coordenada = sc.next();
                    sc.nextLine();
                    prueba.stringToCoord(coordenada);
                    System.out.println("Se ha modificado la coordenada creada con la nueva introducida ahora.");
                    System.out.print("Su coordenada es: ");
                    prueba.printxy();
                    break;
                case 7:
                    System.out.println("Ha elegido: CoordenadaToString");
                    System.out.println("Su coordenada traducida en el tablero es: "+ prueba.coordToString());
                    break;
                case 8: 
                    estado = 1000;
                    System.out.println("Gracias. Que tenga un buen dia.");
                    break;
            }
        }
    }
}
