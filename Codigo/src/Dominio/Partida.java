package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Ficha;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class Partida {
    private Problema p = new Problema();
    private double time;
    private Jugador player1;
    private Jugador player2;
    private boolean turno;

    public Partida() {}
    
    public Partida(Usuario j1,Usuario j2,Problema  p) {
        player1 = j1;
        player2 = j2;
        this.p = p;
    }
    
    public Partida(Usuario j1,Maquina m,Problema  p ) {
        player1 = j1;
        player2 = m ; 
        this.p = p;
    }
    
    public Partida(Maquina m1, Maquina m2, Problema p) {
        player1 = m1;
        player2 = m2;
        this.p = p;
    }
    
    public void playJugadores() {
        int cont = 0;
        String coordenada1,coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        
        while (cont < (p.getNumMovimientos()*2) && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            System.out.println("Introduzca coordenada origen, ex e4: ");
            coordenada1 = sc.next();
            sc.nextLine();
            System.out.println("Introduzca coordenada final, ex e4: ");
            coordenada2 = sc.next();
            sc.nextLine();
            int res = mover(turno,coordenada1,coordenada2);
            if (res == 0) {
                if (p.checkmate(turno)){
                    win = true;
                    System.out.println("Fin del juego. Ganan las "+t);//DECIR LOS MOVIMIENTOS? Y EL TIEMPO
                }
                turno = !turno;
                ++cont;
            }
        }
    }
    
    public int mover(boolean color,String cord1,String cord2 ){
        Coordenada c1 = new Coordenada();
        c1.stringToCoord(cord1);
        Coordenada c2 = new Coordenada();
        c2.stringToCoord(cord2);
        Ficha o = p.getFicha(c2);
        if(color == p.getFicha(c1).getColor() && p.moveFicha(cord1,cord2)){
            if (p.mate(!p.getFicha(c2).getColor())) {
                System.out.println("Estás en jaque. Vuelve a intentarlo.");
                p.undoFicha(c2.coordToString(),c1.coordToString(),o);
                return -1;
            }
            else {
                return 0;
            }
        }
        else {
            System.out.println("Vuelva a intentarlo");
            return -1;
        }  
  }
}
