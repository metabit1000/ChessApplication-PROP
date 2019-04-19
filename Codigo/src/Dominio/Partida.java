package Dominio;

import ClasesExtra.Coordenada;
import java.util.Scanner;

/**
 *
 * @author Jordi
 */
public class Partida {
    private Problema p = new Problema();
    private double time ;
    private Jugador player1;
    private Jugador player2;

    public Partida() {
    }
    
    public Partida(Jugador j1,Jugador j2,Problema  p) {
        player1 = j1;
        player2 = j2;
        this.p = p;
        
    }
    
    public Partida(Jugador j1,Maquina m,Problema  p ) {
        player1 = j1;
        player2 = m ; 
        this.p = p;
    }

    public void jugar(Boolean color,String cord1 ,String cord2 ){//Jugador no maquina 
        Scanner sc = new Scanner(System.in);
        Coordenada c = new Coordenada();
        c.stringToCoord(cord1);
        if(color == p.getFicha(c).getColor()){
            p.moveFicha(cord1,cord2);
            p.printTablero();
        }
        else System.out.println("Incorrecto "); 
  }
}
