package Dominio;
import ClasesExtra.Coordenada;
import Dominio.fichas.*;

import java.util.*;

/**
 *
 * @author joan
 */
public class ConjuntoProblemas {

    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Problema prueba = new Problema();

        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Meter el fen");
            System.out.println("2. Mover ficha");
            System.out.println("3. MatrixToFen");
            System.out.println("4. Crear Problema");
            System.out.println("5. Exit");
            
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    String fen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B b";
                    prueba.fenToMatrix(fen);
                    prueba.printTablero();
                    break;
                case 2:
                    String c1;
                    String c2;
                    Coordenada c;
                    System.out.println("Ha elegido: Mover ficha");
                    System.out.println("Introduzca un Coordenada de origen: ");
                    c1 = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada de destino: ");
                    c2 = sc.next();
                    sc.nextLine();
                    prueba.moveFicha(c1,c2);
                    prueba.printTablero();
                    break;
                case 3: 
                    String lol = prueba.matrixToFen();
                    prueba.fenToMatrix(lol);
                    prueba.printTablero();
                    break;
                case 4:
                    System.out.println("Ha escogido: Crear problema.");
                    Problema crear = new Problema();
                    crear.printTablero();
                    System.out.println("Desea insertar fichas? (y/n)");
                    String resp = sc.next();
                    sc.nextLine();
                    int numr = 0;
                    int nump = 0;
                    int numn = 0;
                    int numq = 0;
                    int numb = 0;
                    int numR = 0;
                    int numP = 0;
                    int numN = 0;
                    int numQ = 0;
                    int numB = 0;
                    
                    Boolean masFicha = false;
                    Boolean hayReyNegro = false;
                    Boolean hayReyBlanco = false;
                    if (resp.equals("y")) masFicha = true;
                    while (masFicha) {
                        System.out.println("Tipo de ficha: (k/q/r/p/n/b)");
                        String ficha = sc.next();
                        sc.nextLine();
                        System.out.println("Color de la ficha: (n/b)");
                        String color = sc.next();
                        sc.nextLine();
                        System.out.println("Coordenada de la ficha: (ejemplo: b6)");
                        String pos = sc.next();
                        sc.nextLine();
                        switch(ficha) {
                            case "r":
                                Rook r;
                                if (color.equals("n")) {
                                    ++numr;
                                    r = new Rook(false, 'r');
                                }
                                else if (color.equals("b")) {
                                    ++numR;
                                    r = new Rook(true, 'R');
                                }
                                else {
                                    System.out.println("Error, vuelva a intentarlo");
                                    break;
                                }
                                if (numR > 2) {
                                    System.out.println("Sólo puede haber 2 Torres Blancas.");
                                    break;
                                }
                                else if(numr > 2) {
                                    System.out.println("Sólo puede haber 2 Torres Negras.");
                                    break;
                                }
                                Coordenada coordR = new Coordenada();
                                coordR.stringToCoord(pos);
                                try {
                                    crear.setFicha(coordR,r);
                                    crear.printTablero();
                                } catch (ArrayIndexOutOfBoundsException e2){
                                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                                    break;
                                }
                                break;
                            case "k":
                                King k;
                                if (color.equals("n")) {
                                    k = new King(false, 'k');
                                    hayReyNegro = true;
                                }
                                else if (color.equals("b")) {
                                    k = new King(true, 'K');
                                    hayReyBlanco = true;
                                }
                                else {
                                    System.out.println("Error, vuelva a intentarlo");
                                    break;
                                }
                                if (numR > 2) {
                                    System.out.println("Sólo puede haber 2 Torres Blancas.");
                                    break;
                                }
                                else if(numr > 2) {
                                    System.out.println("Sólo puede haber 2 Torres Negras.");
                                    break;
                                }
                                Coordenada coordK = new Coordenada();
                                coordK.stringToCoord(pos);
                                try {
                                    crear.setFicha(coordK,k);
                                } catch (ArrayIndexOutOfBoundsException e2){
                                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                                    break;
                                }
                                break;
                            case "q":
                                Queen q;
                                if (color.equals("n")) {
                                    ++numq;
                                    q = new Queen(false, 'q');
                                }
                                else if (color.equals("b")) {
                                    ++numQ;
                                    q = new Queen(true, 'Q');
                                }
                                else {
                                    System.out.println("Error, vuelva a intentarlo");
                                    break;
                                }
                                Coordenada coordQ = new Coordenada();
                                coordQ.stringToCoord(pos);
                                try {
                                    crear.setFicha(coordQ,q);
                                } catch (ArrayIndexOutOfBoundsException e2){
                                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                                    break;
                                }
                                break;
                            case "p":
                                Pawn p;
                                if (color.equals("n")) {
                                    ++nump;
                                    p = new Pawn(false, 'p');
                                }
                                else if (color.equals("b")) {
                                    ++numP;
                                    p = new Pawn(true, 'P');
                                }
                                else {
                                    System.out.println("Error, vuelva a intentarlo");
                                    break;
                                }
                                Coordenada coordP = new Coordenada();
                                coordP.stringToCoord(pos);
                                try {
                                    crear.setFicha(coordP,p);
                                } catch (ArrayIndexOutOfBoundsException e2){
                                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                                    break;
                                }
                                break;
                            case "b":
                                Bishop b;
                                if (color.equals("n")) {
                                    ++numb;
                                    b = new Bishop(false, 'b');
                                }
                                else if (color.equals("b")) {
                                    ++numB;
                                    b = new Bishop(true, 'B');
                                }
                                else {
                                    System.out.println("Error, vuelva a intentarlo");
                                    break;
                                }
                                Coordenada coordB = new Coordenada();
                                coordB.stringToCoord(pos);
                                try {
                                    crear.setFicha(coordB,b);
                                } catch (ArrayIndexOutOfBoundsException e2){
                                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                                    break;
                                }
                                break;
                            case "n":
                                Knight n;
                                if (color.equals("n")) {
                                    ++numn;
                                    n = new Knight(false, 'n');
                                }
                                else if (color.equals("b")) {
                                    ++numN;
                                    n = new Knight(true, 'N');
                                }
                                else {
                                    System.out.println("Error, vuelva a intentarlo");
                                    break;
                                }
                                Coordenada coordN = new Coordenada();
                                coordN.stringToCoord(pos);
                                try {
                                    crear.setFicha(coordN,n);
                                } catch (ArrayIndexOutOfBoundsException e2){
                                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                                    break;
                                }
                                break;
                            

                        }
                        crear.printTablero();
                        System.out.println("Desea insertar fichas? (y/n)");
                        String resp1 = sc.next();
                        sc.nextLine();
                        if (!resp1.equals("y")) masFicha = false;
                    }
                    if (!hayReyNegro & !hayReyBlanco) System.out.println("Es necesario crear un rey de cada color");
                    else if (!hayReyNegro) System.out.println("Falta el rey de las fichas negras");
                    else if (!hayReyBlanco) System.out.println("Falta el rey de las fichas blancas");
                    else {
                        System.out.println("Quien empieza el turno? (n/b)");
                        String t = sc.next();
                        sc.nextLine();
                        if (t.equals("n")) crear.setTurno(false);
                        else crear.setTurno(true);
                        //>Problema creado. la maquina lo ha pasado con X movimientos
                        //>Qué nombre deseas ponerle?
                        String p = crear.matrixToFen();
                        System.out.println("El fen del priblema es: "+ p);
                        //añadir al map de <IDproblema, fen>
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
