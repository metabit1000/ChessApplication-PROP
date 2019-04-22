package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Àlex
 */
public class DriverRook {
     public void testConstructor() {}
     public void testPosiblesMovimientos() {}
     
     public static void main (String [] args) { 
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        char color;
        Problema p = new Problema();
        Coordenada c1 = new Coordenada();
        Coordenada c2 = new Coordenada();
        Rook prueba = new Rook();
        String coordenada;
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructora");
            System.out.println("2. PosiblesMovimientos");
            System.out.println("3. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    Integer x,y;
                    System.out.println("Ha elegido: Constructora");
                    System.out.println("Introduzca un color (n /b): ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    if (color == 'n') prueba = new Rook(false,'r');
                    else if (color == 'b') prueba = new Rook(true,'R'); 
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    }
                    p.printTablero();
                    System.out.println("Introduzca coordenada, ex e4: ");
                    coordenada = sc.next();
                    sc.nextLine();
                    c1.stringToCoord(coordenada);
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
                    Rook bueno = new Rook();
                    ArrayList<Coordenada> res = new ArrayList();
                    System.out.println("Ha elegido: PosiblesMovimientos");
                    System.out.println("Introduzca coordenada, ex e4: ");
                    coordenada = sc.next();
                    sc.nextLine();
                    c2.stringToCoord(coordenada);
                    try {
                        bueno = (Rook)p.getFicha(c2);
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
