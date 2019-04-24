package Dominio;

import ClasesExtra.*;
import Dominio.fichas.Ficha;
import java.util.Scanner;
import java.lang.System; //para nanotime()

/**
 *
 * @author Joan
 */
public class Partida {
    private Problema p = new Problema();
    private long time;
    private Jugador player1;
    private Jugador player2;
    private boolean turno;

    public Partida() {}
    
    public Partida(Usuario j1,Usuario j2,Problema p) {
        player1 = j1;
        player2 = j2;
        this.p = p;
    }
    
    public Partida(Usuario j1,Maquina m,Problema  p) {
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
        long t1 = 0;
        long t2 = 0;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        //No se si aqui hay que introducir a los usuarios en el ranking
        while (cont < (p.getNumMovimientos()*2) && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            Pair <Coordenada,Coordenada> moves  = new Pair();
            if (turno){
                long startTime1 = System.nanoTime();    
                moves = player1.getNextMove(p);
                t1 += (System.nanoTime() - startTime1);
            }
            else {
                long startTime2 = System.nanoTime();   
                moves = player2.getNextMove(p);
                t2 = (System.nanoTime() - startTime2);
            }
            int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
            if (res == 0) {
                if (p.checkmate(turno)){
                    p.printTablero();
                    win = true;
                    long k;
                    if (turno) k = t1;
                    else k = t2;
                    System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                    p.actualizarRanking(player1.getNombre(), k);
                }
                turno = !turno;
                
            }
            ++cont;
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
    }
    
    public void playJugadorVSMaquina() {
        int cont = 0;
        String coordenada1,coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        long t1 = 0;
        long t2 = 0;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        p.introducirElemento(player1.getNombre(), 0);
        while (cont < (p.getNumMovimientos()*2) && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            Pair <Coordenada,Coordenada> moves  = new Pair();
            boolean T = player2.getColor();
            if (player1.getColor()) {
                if (T == turno) {
                    moves = player2.getNextMove(p);
                    p.moveFicha(moves.getKey().coordToString(), moves.getValue().coordToString());
                    turno = !turno;
                }
                else {
                    if (turno) {
                        long startTime1 = System.nanoTime();
                        moves = player1.getNextMove(p);
                        t1 += (System.nanoTime() - startTime1);
                    }
                    else moves = player2.getNextMove(p);
                    int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
                    if (res == 0) {
                        if (p.checkmate(turno)){
                            p.printTablero();
                            win = true;
                            long k;
                            if (turno) k = t1;
                            else k = t2;
                            System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                            p.actualizarRanking(player1.getNombre(), k);
                        }
                        turno = !turno;
                    }
                }
            }
            else {
                if (T == !turno) {
                    if (!turno) {
                        long startTime1 = System.nanoTime();
                        moves = player1.getNextMove(p);
                        t1 += (System.nanoTime() - startTime1);
                    }
                    else moves = player2.getNextMove(p);
                    int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
                    if (res == 0) {
                        if (p.checkmate(turno)){
                            p.printTablero();
                            win = true;
                            long k;
                            if (turno) k = t2;
                            else k = t1;
                            System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                            p.actualizarRanking(player1.getNombre(), k);
                        }
                        turno = !turno;
                    }
                }
                else {
                    moves = player2.getNextMove(p);
                    p.moveFicha(moves.getKey().coordToString(), moves.getValue().coordToString());
                    turno = !turno;
                }
            }
            ++cont;
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
    }
    
    public void playMaquinaVSMaquina() {
        int cont = 0;
        String coordenada1,coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        while (cont < (p.getNumMovimientos()*42) && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            Pair <Coordenada,Coordenada> moves  = new Pair();
            if (turno) {
                ++cont;
                moves = player1.getNextMove(p);
            }
            else moves = player2.getNextMove(p);
            p.moveFicha(moves.getKey().coordToString(),moves.getValue().coordToString());
            if (p.checkmate(turno)){
                win = true;
                System.out.println("Fin del juego. Ganan las "+t);
            }
            turno = !turno;
            
            System.out.println(cont);
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
