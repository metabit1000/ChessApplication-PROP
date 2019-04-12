package Dominio;
import ClasesExtra.Coordenada;

import java.util.*;

/**
 *
 * @author joan
 */
public class ConjuntoProblemas {
//    public void testCofnstructor() {}
//    public void testGetCoordenada() {}
//    public void testGetColor() {}
//    public void testsetPosicion() {}
//    testPosiblesMovimientos en cada subclase
    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Problema prueba = new Problema();

        while (estado != fin) {
            System.out.println("Usuarios:");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Mover ficha");
            
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
                    Integer x1;
                    Integer y1;
                    Integer x2;
                    Integer y2;
                    System.out.println("Ha elegido: Mover ficha");
                    System.out.println("Introduzca un Coordenada x1: ");
                    x1 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada y1: ");
                    y1 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada x2: ");
                    x2 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduzca un Coordenada y2: ");
                    y2 = sc.nextInt();
                    sc.nextLine();
                    Coordenada c1 = new Coordenada(x1,y1);
                    Coordenada c2 = new Coordenada(x2,y2);
                    prueba.moveFicha(c1,c2);
                    prueba.printTablero();
                    break;
//                case 3: 
//                    String nomLO;
//                    System.out.println("Ha elegido: Cerrar sesión");
//                    System.out.println("Introduzca un nombre: ");
//                    nomLO = sc.next();
//                    sc.nextLine();
//                    prueba.logoutUsuario(nomLO);
//                    System.out.println("Se ha cerrado la sesión");
//                    break;
//                case 4:
//                    String nomMP;
//                    String passMP;
//                    System.out.println("Ha elegido: Modificar contraseña");
//                    System.out.println("Introduzca un nombre: ");
//                    nomMP = sc.next();
//                    sc.nextLine();
//                    System.out.println("Introduzca una nueva constraseña: ");
//                    passMP = sc.next();
//                    sc.nextLine();
//                    prueba.modificarPassword(nomMP, passMP);
//                    System.out.println("La contraseña ha sido cambiada satisfactoriamente");
//                    break;
//                case 5:
//                    prueba.printUsuarios();
//                    break;
//                case 6: 
//                    fin = 5;
//                    System.out.println("Gracias. Que tenga un buen día.");
//                    break;
            }
        }
    }
}
