package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Ficha;
import java.lang.System; //para nanotime()
import java.util.ArrayList;

/**
 *
 * @author Joan
 */
public class Partida {
    private Problema p = new Problema();
    private Jugador player1;
    private Jugador player2;

    public Partida() {}
    
    public Partida(Usuario j1,Usuario j2,Problema p) {
        //creadora de partida para dos jugadores usuarios
        player1 = j1;
        player2 = j2;
        this.p = p;
    }
    
    public Partida(Usuario j1,Maquina m,Problema  p) {
        //creadora de partida para dos jugadores: un usuario y una maquina
        player1 = j1;
        player2 = m ; 
        this.p = p;
    }
    
    public Partida(Maquina m1, Maquina m2, Problema p) {
        //creadora de partida para dos jugadores maquina
        player1 = m1;
        player2 = m2;
        this.p = p;
    }

    public boolean getTurnoInicial() {
        return p.getTurno(); //turno inicial del problema
    }
    
    public int getNumMovimientos() {
        return p.getNumMovimientos();  //num movimientos del problema
    }
    
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        ArrayList<Coordenada> res = p.getFicha(c).posiblesMovimientos(p, c);
        return res;
    }
    
    public Problema getProblema() {
        return p;
    }
    
    public boolean checkMate(boolean turno) {
        return p.checkmate(turno);
    }
    
    public boolean getColor(Coordenada c) {
        return p.getFicha(c).getColor();
    }
    
    public void actualizarRanking(String nombre,double tiempo) {
        if (p.existeix(nombre)) p.actualizarRanking(nombre, tiempo);
        else p.introducirElemento(nombre,tiempo);
    }
    
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal){ //color es el turno
        Ficha o = p.getFicha(cordFinal);
        int res = 0; //todo correcto
        if(color == p.getFicha(cordInicio).getColor() && p.moveFicha(cordInicio,cordFinal)){
            if (p.mate(!p.getFicha(cordFinal).getColor())) {
                p.undoFicha(cordFinal,cordInicio,o);
                res = -1; //"Estás en jaque. Vuelve a intentarlo."
            }
        } 
        else res = -2;  //No es tu turno
        return res;
    }
    
   /* public void playJugadorVSMaquina() {
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
        boolean pt = p.getTurno();
        while (cont < p.getNumMovimientos() && !win) {
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
                        if (turno == pt) ++cont;
                    }
                    else moves = player2.getNextMove(p);
                    int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
                    if (res == 0) {
                        //if (turno == pt) ++cont;
                        //System.out.println(cont);
                        if (p.checkmate(turno)){
                            p.printTablero();
                            win = true;
                            long k;
                            if (turno) k = t1;
                            else k = t2;
                            System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                            String winner;
                            if (turno == player1.getColor()) winner = player1.getNombre();
                            else winner = player2.getNombre();
                            if (!p.existeix(winner)) p.introducirElemento(winner, k);
                            else p.actualizarRanking(player1.getNombre(), k);
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
                        if (turno == pt) ++cont;
                    }
                    else moves = player2.getNextMove(p);
                    int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
                    if (res == 0) {
                        if (turno == pt) ++cont;
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
                    if (turno == pt) ++cont;
                    turno = !turno;
                }
            }
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
    }
    
    public int playMaquinaVSMaquina(boolean validar) {
        int cont = 0;
        String coordenada1, coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        boolean pt = p.getTurno();
        int compare;
        if (!validar) compare = p.getNumMovimientos();
        else compare = 50;
        p.printTablero();
        while (cont < compare && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            Pair <Coordenada,Coordenada> moves  = new Pair();
            if (turno) {
                moves = player1.getNextMove(p);
                if (turno == pt) {
                    ++cont;
                }
                
            }
            else {
                moves = player2.getNextMove(p);
                if (turno == pt) {
                    ++cont;
                }
            }
            p.moveFicha(moves.getKey().coordToString(),moves.getValue().coordToString());
            if (p.checkmate(turno)){
                win = true;
                System.out.println("Fin del juego. Ganan las "+t);
            }
            p.printTablero();
            turno = !turno;
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
        return cont;
    }
    */
    
}
