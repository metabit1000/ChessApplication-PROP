package Dominio;

import ClasesExtra.Coordenada;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class DriverPartida {
    
    public static void main (String [] args) {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Problema prueba = new Problema(2);
   
        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Tablero");
            System.out.println("2. Jugar");
            System.out.println("3. Exit");
            
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            char color;
            switch (estado) {
                 case 1:
                    String fen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w";
                    prueba.fenToMatrix(fen);
                    prueba.printTablero();
                    break;
                case 2:
                    Partida partida = new Partida();
                    Usuario us1 = new Usuario();
                    Usuario us2 = new Usuario();
                    System.out.println("Ha elegido: Jugar");
                    System.out.println("Introduzca opción que desea jugar: ");
                    System.out.println("1. JugadorVsJugador"); //faltaria loguearse
                    System.out.println("2. JugadorVsMaquina1");
                    System.out.println("3. JugadorVsMaquina2");
                    System.out.println("4. Maquina1VsMaquina2");
                    int opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            System.out.println("Jugador1, ¿color?");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n'){
                                us1 = new Usuario(false);
                                us2 = new Usuario(true);
                            }
                            else if (color == 'b') {
                                us1 = new Usuario(true);
                                us2 = new Usuario(false);
                            } 
                            else {
                                System.out.println("Error, vuelva a intentarlo");
                                break;
                            }
                            partida = new Partida(us1,us2,prueba);
                            System.out.println("Partida creada, jugador2 juega con; "+ us2.getcolor());
                            partida.playJugadores();
                            break;
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
