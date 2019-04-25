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
            System.out.println("Introduzca un nÃºmero: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    System.out.println("Ha escogido: Crear problema.");
                    Problema crear = new Problema("8/8/8/8/8/8/8/8/ w", 3);
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
                    System.out.println("En cuÃ¡ntos movimientos gana?");
                    int x = sc.nextInt();
                    sc.nextLine();
                    crear.setNumMovimientos(x);
                    Maquina m1 = new Maquina(tp,"m1",1, x);
                    Maquina m2 = new Maquina(!tp,"m2",1, x);
                    Partida part = new Partida(m1,m2,crear);
                    int b = part.playMaquinaVSMaquina();
                   if(b == x )                     System.out.println("Problema superado en "+ x+" movimientos");
                   else{
                       System.out.println("El problema se puede superar en "+ b+" movimientos, s");
                       
                   }
                    System.out.println("Quieres aÃ±adirlo ");
                    boolean c  = sc.nextBoolean();
                    sc.nextLine();
                    if(c){
                    System.out.println("Nombre del problema:");
                    String id = sc.next();
                    sc.nextLine();
                    String p = crear.matrixToFen();
                    cp.addProblema(id,p,x);
                    System.out.println("Problema aÃ±adido.");
                    }
                    break;
                case 2:
                    cp.printProblemas();
                    break;
                    
                case 3:
                    fin = 3;
                    System.out.println("Gracias. Que tenga un buen dÃ­a.");
                    break;
            }
        }
    }
}
