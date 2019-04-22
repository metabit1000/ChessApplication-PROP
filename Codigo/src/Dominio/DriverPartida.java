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
            
            //faltaria logout y hacer booleano en usuarios que diga si un usuario está logeado..de cara a la segunda entrega
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    Partida partida = new Partida();
                    char color;
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
                    System.out.println("1. JugadorVsJugador"); 
                    System.out.println("2. JugadorVsMaquina1");
                    System.out.println("3. Maquina1VsMaquina1");
                    System.out.println("4. Maquina1VsMaquina2");
                    System.out.println("5. Maquina2VSMaquina2");
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("JugadorVsJugador");
                            Usuario us1 = new Usuario();
                            Usuario us2 = new Usuario();
                            System.out.println("Jugador1, ¿color? (n/b)");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n') {
                                us1 = new Usuario(false,"Jose");
                                us2 = new Usuario(true,"Maria");
                            } else if (color == 'b') {
                                us1 = new Usuario(true,"Jose");
                                us2 = new Usuario(false,"Maria");
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
                            System.out.println("JugadorVsMaquina1");
                            Usuario usjm = new Usuario();
                            Maquina m = new Maquina();
                            System.out.println("Jugador, ¿color? (n/b)");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n') {
                                usjm = new Usuario(false,"Jose");
                                m = new Maquina(true, "m1",1);
                            } else if (color == 'b') {
                                usjm = new Usuario(true,"Jose");
                                m = new Maquina(false,"m1", 1);
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
                        case 3: 
                            System.out.println("Maquina1VsMaquina1");
                            Maquina m1 = new Maquina(true,"m1",1);
                            Maquina m2 = new Maquina(false,"m2",1);
                            System.out.println("Color de cada maquina escogido aleatoriamente");
                            partida = new Partida(m1, m2, prueba);
                            System.out.println("Partida creada correctamente");
                            partida.playMaquinaVSMaquina();
                            break;
                        case 4: 
                            System.out.println("Maquina1VsMaquina2");
                            System.out.println("No implementado aún; segunda entrega");
                            break;
                        case 5: 
                            System.out.println("Maquina2VsMaquina2");
                            System.out.println("No implementado aún; segunda entrega");
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
