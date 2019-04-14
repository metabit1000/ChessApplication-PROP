package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Àlex
 */
public class DriverQueen {

    public static void main (String [] args) { 
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        char color;
        Problema p = new Problema();
        Coordenada c1 = new Coordenada();
        Coordenada c2 = new Coordenada();
        Queen prueba = new Queen();
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
                    if (color == 'n') prueba = new Queen(false,'q');
                    else if (color == 'b') prueba = new Queen(true,'Q'); 
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
                    System.out.println("Ha elegido: PosiblesMovimientos");
                    System.out.println("Introduzca una coordenada del tablero(x): ");
                    x = sc.nextInt();
                    System.out.println("Introduzca una coordenada del tablero(y): ");
                    y = sc.nextInt();
                    c2 = new Coordenada(x,y);
                    Queen bueno = (Queen)p.getFicha(c2);
                    p.printTablero();
                    ArrayList<Coordenada> res = bueno.posiblesMovimientos(p,c2);
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
