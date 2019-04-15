package Dominio.fichas;


import Dominio.Problema;
import ClasesExtra.Coordenada;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Àlex
 */
public class DriverPawn {

    public static void main (String [] args) {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        char color;
        Problema p = new Problema();
        Coordenada c1 = new Coordenada();
        Coordenada c2 = new Coordenada();
        Pawn prueba = new Pawn();
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. PosiblesMovimientos");
            System.out.println("3. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    Integer x,y;
                    System.out.println("Ha elegido: Constructor");
                    System.out.println("Introduzca un color (n /b): ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    if (color == 'n') prueba = new Pawn(false,'p');
                    else if (color == 'b') prueba = new Pawn(true,'P'); 
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    System.out.println("Introduzca una coordenada del tablero(x): ");
                    x = sc.nextInt();
                    System.out.println("Introduzca una coordenada del tablero(y): ");
                    y = sc.nextInt();
                    c1 = new Coordenada(x,y);
                    try {
                        p.setFicha(c1,prueba);
                        p.printTablero();
                    } catch (ArrayIndexOutOfBoundsException e2){
                        System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                        break;
                    }
                    System.out.println("Ficha creada correctamente");
                    break;
                case 2:
                    Pawn bueno = new Pawn();
                    ArrayList<Coordenada> res = new ArrayList();
                    System.out.println("Ha elegido: PosiblesMovimientos");
                    System.out.println("Introduzca una coordenada del tablero(x): ");
                    x = sc.nextInt();
                    System.out.println("Introduzca una coordenada del tablero(y): ");
                    y = sc.nextInt();
                    c2 = new Coordenada(x,y);
                    try {
                        bueno = (Pawn)p.getFicha(c2);
                    } catch (ArrayIndexOutOfBoundsException e3) {
                        System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                        break;
                    }
                    p.printTablero();
                    try {
                        res = bueno.posiblesMovimientos(p,c2);
                    } catch (NullPointerException e4) {
                        System.out.println("No hay ficha en esa posicion del tablero, vuelva a intentarlo");
                        break;
                    }
                    for(int i=0;i<res.size();i++) {
                        res.get(i).printxy();
                    }
                    break;
                case 3: 
                    fin = 3;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}
