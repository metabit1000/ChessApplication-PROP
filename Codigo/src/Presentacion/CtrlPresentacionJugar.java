package Presentacion;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import Dominio.CtrlPartida;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Àlex
 */
public class CtrlPresentacionJugar {
    private int id; //id del problema a jugar
    private int tipo; //tipo, se pondra en la constructora
    private CtrlPartida ctrlP = new CtrlPartida(0,0,2); //prueba
    
    public char[][] getTablero(int id) {
        char[][] res = ctrlP.getTablero(id);
        return res;
    }
    
    public boolean getColor(Coordenada c) {
        return ctrlP.getColor(c);
    }
    
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        return ctrlP.posiblesMovimientos(c);
    }
    
    public boolean getTurnoInicial() {
        return ctrlP.getTurnoInicial();
    }
    
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal) {
        return ctrlP.moverFicha(color, cordInicio, cordFinal);
    }
    
    public boolean checkMate(boolean turno) {
        return ctrlP.checkMate(turno);
    }
    
    /*public void playJugadores() {
        int cont = 0;
        String coordenada1,coordenada2;
        boolean turno = ctrlP.getTurnoInicial(); //obtengo quien empieza jugando
        boolean pt = ctrlP.getTurnoInicial(); //turno auxiliar para el contador
        String t;
        Pair <Coordenada,Coordenada> moves  = new Pair();
        long t1 = 0; //tiempo
        long t2 = 0; //tiempo
        boolean win = false; //nadie ha ganado aun
        if (turno) t = "blancas.";
        else t = "negras.";
        JOptionPane.showMessageDialog(null, "El turno es de las " + t);
        while (cont < (ctrlP.getNumMovimientos()) && !win) {
            if (turno) t = "blancas.";
            else t = "negras.";
            if (cont != 0) JOptionPane.showMessageDialog(null, "El turno es de las " + turno);
            if (turno){
                long startTime1 = System.nanoTime();    //ya vere que hago con el tiempo
                //coger el movimiento que quiere hacer el usuario1 (tendria que ser comprobado como bueno ya en la vista)
                JOptionPane.showMessageDialog(null, "Haga su movimiento");
                boolean b = true;
                while(b) {if(v.movimientoHecho()) b = false;} //COMO coño hago esto xd
                Coordenada posInicial = v.getPosicionInicial(); //COMO HACE EL USUARIO EL MOVIMIENTO?
                System.out.println(posInicial.getX()+" "+posInicial.getY());
                //vista.getPosiconFinal();
                //movimiento en presentacion estara hecho
                //hacer el movimiento en dominio para el siguiente posibleMovimiento(abajo)
                t1 += (System.nanoTime() - startTime1);
                if (turno == pt) ++cont;
            }
            else {
                long startTime2 = System.nanoTime();   
                //coger el movimiento que quiere hacer el usuario2 (tendria que ser comprobado como bueno ya en la vista)
                t2 = (System.nanoTime() - startTime2);
                if (turno == pt) ++cont;
            }
            int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString()); //actualizar en dominio
            if (res == 0) {
                if (p.checkmate(turno)){ //comprobarlo en dominio
                    //printablero
                    win = true;
                    long k;
                    if (turno) k = t1;
                    else k = t2;
                    System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos."); //sacarlo por pantalla el ganador
                    String winner;
                    if (turno == player1.getColor()) winner = player1.getNombre();
                    else winner = player2.getNombre(); 
                    if (!p.existeix(winner)) p.introducirElemento(winner, k); //introducir en el ranking que hay en partida (dominio)
                    else p.actualizarRanking(player1.getNombre(), k);
                }
                turno = !turno;
            }
        }
        if (!win) {
            //ganaria el contrincante, avisar de ello por pantalla y decir el motivo, que es haber excedido el numDeMovimientos
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
    }*/
    
    //TODA LA MIERDA DE JUGAR (EL WHILE)
}
