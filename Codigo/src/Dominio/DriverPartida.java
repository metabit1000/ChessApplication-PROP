package Dominio;

import ClasesExtra.Coordenada;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class DriverPartida {

    public static void main(String[] args) {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Problema prueba = new Problema(2);
        CtrlProblema cp = new CtrlProblema();
        while (estado != fin) {
            System.out.println("Partida:");
            System.out.println("1. Jugar");
            System.out.println("2. Exit");

            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            char color;
            switch (estado) {
                case 1:
                    Partida partida = new Partida();
                    System.out.println("Ha elegido: Jugar");
                    System.out.println("Lista de los problemas registrados:");
                    cp.printProblemas();
                    System.out.println("Introduzca el índice del problema que desea jugar: ");
                    int problema = sc.nextInt();
                    sc.nextLine();
                    String f = cp.seleccionProblema(problema);
                    prueba.fenToMatrix(f);
                    prueba.printTablero();
                    System.out.println("Introduzca opción que desea jugar: ");
                    System.out.println("1. JugadorVsJugador"); //faltaria loguearse
                    System.out.println("2. JugadorVsMaquina1");
                    System.out.println("3. JugadorVsMaquina2");
                    System.out.println("4. Maquina1VsMaquina2");
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            Usuario us1 = new Usuario();
                            Usuario us2 = new Usuario();
                            System.out.println("Jugador1, ¿color? (n/b)");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n') {
                                us1 = new Usuario(false);
                                us2 = new Usuario(true);
                            } else if (color == 'b') {
                                us1 = new Usuario(true);
                                us2 = new Usuario(false);
                            } else {
                                System.out.println("Error, vuelva a intentarlo");
                                break;
                            }
                            partida = new Partida(us1, us2, prueba);
                            String c;
                            if (us2.getcolor()) {
                                c = "blancas.";
                            } else {
                                c = "negras";
                            }
                            System.out.println("Partida creada, jugador2 juega con " + c);
                            partida.playJugadores();
                            break;
                        case 2:
                            Usuario usjm = new Usuario();
                            Maquina m = new Maquina();
                            System.out.println("Jugador, ¿color? (n/b)");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n') {
                                usjm = new Usuario(false);
                                m = new Maquina(true, 1);
                            } else if (color == 'b') {
                                usjm = new Usuario(true);
                                m = new Maquina(false, 1);
                            } else {
                                System.out.println("Error, vuelva a intentarlo");
                                break;
                            }
                            partida = new Partida(usjm, m, prueba);
                            String c2;
                            if (usjm.getcolor()) {
                                c2 = "blancas.";
                            } else {
                                c2 = "negras";
                            }
                            System.out.println("Partida creada, Jugador1 juega con " + c2);
                            partida.playJugadorVSMaquina();
                            break;

                    }
                    break;
                case 2:
                    fin = 2;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}
