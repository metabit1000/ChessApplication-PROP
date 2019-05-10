package Dominio;
import ClasesExtra.Coordenada;
import Dominio.fichas.*;

import java.util.*;

/**
 *
 * @author joan
 */
public class DriverCtrlProblemas {

    public static void main (String [] args) throws Exception {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        CtrlProblemas cp = new CtrlProblemas();
        while (estado != fin) {
            System.out.println("Controlador de Problema:");
            System.out.println("1. Crear problema");
            System.out.println("2. Mostrar problemas");
            System.out.println("3. Exit");
            Problema prueba = new Problema();
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    System.out.println("Ha escogido: Crear problema.");
                    Problema crear = new Problema(1,"8/8/8/8/8/8/8/8/ w", 100, new Ranking());
                    crear.printTablero();
                    System.out.println("Desea insertar fichas? (y/n)");
                    String resp = sc.next();
                    sc.nextLine();
                    boolean masFicha = false;
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
                        cp.anadirFicha(crear, ficha, color, pos);
                        crear.printTablero();
                        System.out.println("Desea insertar fichas? (y/n)");
                        String resp1 = sc.next();
                        sc.nextLine();
                        if (!resp1.equals("y")) masFicha = false;
                    }
                    if (!cp.cumpleRestriccionFichas(crear)) {
                        break;
                    }
                    System.out.println("Quien empieza el turno? (n/b)");
                    String t = sc.next();
                    sc.nextLine();
                    boolean tp;
                    if (t.equals("n")) tp = false;
                    else tp = true;
                    crear.setTurno(tp);
                    System.out.println("En cuántos movimientos gana?");
                    int x = sc.nextInt();
                    sc.nextLine();
                    Maquina m1 = new MaquinaEasy(tp,"m1",x);
                    Maquina m2 = new MaquinaEasy(!tp,"m2", x);
                    Partida h = new Partida(m1,m2,crear);
                    if (x == h.playMaquinaVSMaquina(true)) {
                        System.out.println("Problema superado en "+ x +" movimientos");
                        String p = crear.matrixToFen();
                        System.out.println("Nombre del problema:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        Problema probl = new Problema(id,p,x,new Ranking());
                        cp.addProblema(probl);
                        System.out.println("Problema añadido.");
                    } 
                    else System.out.println("El problema no se resuelve en "+x+" movimientos.");
                    break;
                case 2:
                    cp.printProblemas();
                    break;
                    
                case 3:
                    fin = 3;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}
