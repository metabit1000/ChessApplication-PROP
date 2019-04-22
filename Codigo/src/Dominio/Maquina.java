package Dominio;

import ClasesExtra.*;
import Dominio.Problema;

/**
 *
 * @author Àlex
 */
public class Maquina extends Jugador {
    int dificultad; //1 facil, 2 dificil
    Minimax minimax1 = new Minimax();
    //MinimaxAlphaBeta minimax2;
    
    public Maquina() {}
    
    public Maquina(Boolean color,String nombre,int dificultad) {
        super(color,nombre);
        this.dificultad = dificultad;
    }
    
    public int getDificultad() {
        return dificultad;
    }
    
    public Pair getNextMove(Problema p) {
        Pair moves = new Pair();
        if(dificultad == 1) moves = minimax1.decisionMinimax(p,3,color); //profundidad 3
        //else if (dificultad == 2) minimax2.decisionMinimax(p); //alphaBeta...siguiente entrega
        return moves;
    }
}
