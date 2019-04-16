/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import ClasesExtra.Coordenada;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class DriverPartida {
    
    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Partida prueba = new Partida();
        Problema prueba2=new Problema();
   
        while (estado != fin) {
            System.out.println("Usuarios:");
                        System.out.println("1. Tablero");
            System.out.println("2. Mover ficha");
            System.out.println("4. Exit");
            
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                 case 1:
                    String fen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B b";
                    prueba2.fenToMatrix(fen);
                    prueba2.printTablero();
                    break;
                case 2:
                    String c1;
                    String c2;
                    boolean b1;
                    boolean b2 ;
                    Coordenada c = new Coordenada();
                    Integer contador = 3 ; 
                    System.out.println("Ha elegido: Mover ficha");
                     System.out.println("Jugador1 introduzca color :true->Blancas , false->Negras");
                       b1=sc.nextBoolean();
                       sc.nextLine();
                       Jugador jugador1= new Jugador(b1);
                       Jugador jugador2= new Jugador(!b1);

                       System.out.println("Blancas mayúsculas ");
                       System.out.println("Negras minúsculas ");
                       while (contador > 0) {
                           if(jugador1.color == true )  System.out.println("Juega el jugador1 Blancas " );
                           else System.out.println("Juega el jugador1 Negras " );
                    System.out.println("Introduzca un Coordenada de origen: " );
                    c1 = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada de destino: ");
                    c2 = sc.next();
                    sc.nextLine();
                    c.stringToCoord(c1);
                    Integer x =c.getX();
                    Integer y =c.getY();
                    boolean col=prueba2.color(x, y);
                    if(col == jugador1.color){
                    prueba.moveFicha(c1,c2);
                    prueba2.printTablero();
                    }
                     else  System.out.println("Incorrecto ");
                      if(jugador2.color ==true )  System.out.println("Juega el jugador2  Blancas" );
                           else System.out.println("Juega el jugador2 Negras  " );
                     System.out.println("Introduzca un Coordenada de origen: ");
                    c1 = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada de destino: ");
                    c2 = sc.next();
                    sc.nextLine();
                    c.stringToCoord(c1);
                     Integer x1 =c.getX();
                    Integer y1 =c.getY();
                    boolean col1=prueba2.color(x1, y1);
                    if(col1 == jugador2.color){
                    prueba.moveFicha(c1,c2);
                    prueba2.printTablero();
                    }
                    else  System.out.println("Incorrecto ");
                      --contador ; 
                       }
                    break;
                 
                case 4: 
                    fin = 4;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}
