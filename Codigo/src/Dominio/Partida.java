package Dominio;

import ClasesExtra.Coordenada;
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
        System.out.println("En este problema, empiezan las: ");
        if (p.getTurno()) System.out.println("Blancas");
        else System.out.println("Negras");
        turno = p.getTurno();
        while (cont < (p.getNumMovimientos()*2)) {
            System.out.println("El turno es de: "+ turno);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            System.out.println("Introduzca coordenada origen, ex e4: ");
            coordenada1 = sc.next();
            sc.nextLine();
            System.out.println("Introduzca coordenada final, ex e4: ");
            coordenada2 = sc.next();
            sc.nextLine();
            int res = mover(turno,coordenada1,coordenada2);
            if (res == -1) --cont; //para volver a intentar
            turno = !turno;
            ++cont;    
        }
    }
    
    public int mover(boolean color,String cord1,String cord2 ){
        Coordenada c = new Coordenada();
        c.stringToCoord(cord1);
        if(color == p.getFicha(c).getColor()){
            p.moveFicha(cord1,cord2);
            p.printTablero();
            return 0;
        }
        else {
            System.out.println("Incorrecto");
            return -1;
        }  
  }
}
