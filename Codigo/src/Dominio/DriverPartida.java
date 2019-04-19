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
        Partida prueba ;
        Problema prueba2=new Problema();
   
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
                    String fen = "1r6/8/2r5/8/3k4/8/P7/K7 b";
                    prueba2.fenToMatrix(fen);
                    prueba2.printTablero();
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
                    System.out.println("3. Maquina1VsMaquina2");
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
                            partida = new Partida(us1,us2,prueba2);
                            break;
                    }
                    
                    
                    b1=sc.nextBoolean();
                       sc.nextLine();
                       Jugador jugador1= new Jugador(b1);
                       Jugador jugador2= new Jugador(!b1);
                       prueba=new Partida (jugador1 , jugador2,prueba2);

                       System.out.println("Blancas mayúsculas ");
                       System.out.println("Negras minúsculas ");
                       boolean mate = false;
                       while (!mate ) {
                           if(b1 == true )  System.out.println("Juega el jugador Blancas " );
                           else System.out.println("Juega el jugador Negras " );
                    System.out.println("Introduzca un Coordenada de origen: " );
                    c1 = sc.next();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada de destino: ");
                    c2 = sc.next();
                    sc.nextLine();
                    prueba.jugar(b1,c1,c2);
                    if(prueba.checkmate(b1)) {
                        mate=true;
                    }                    
                    b1 = !b1;
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
