package Dominio;

import ClasesExtra.*;

/**
 *
 * @author Àlex
 */
public class Maquina extends Jugador {
    int dificultad; //1 facil, 2 dificil
    Minimax minimax1 = new Minimax();
    int d;
    //MinimaxAlphaBeta minimax2;
    
    public Maquina() {}
    
    public Maquina(Boolean color,String nombre,int dificultad, int profundidad) {
        super(color,nombre);
        this.dificultad = dificultad;
        d = profundidad;
    }
    
    public int getDificultad() {
        return dificultad;
    }
    
    public Pair getNextMove(Problema p) {
        Pair moves = new Pair();
        if(dificultad == 1){
            if (d == 1) moves = minimax1.decisionMinimax(p,3,color); //se queda corto de movimientos...y no lo hace bien con 2d-1
            else moves = minimax1.decisionMinimax(p,2*d-1,color); 
        } 
        //else if (dificultad == 2) minimax2.decisionMinimax(p); //alphaBeta...siguiente entrega
        return moves;
    }
}
