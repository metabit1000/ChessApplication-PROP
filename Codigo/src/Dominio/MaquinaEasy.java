package Dominio;

import ClasesExtra.*;

/**
 *
 * @author Jordi
 */
public class MaquinaEasy extends Maquina {
    MaquinaEasy() {}
    
    MaquinaEasy(Boolean color,int profundidad) {
        super(color,profundidad);
    }
    
    /**
    * pre: Problema no nulo
    * post: Devuelve el mejor movimiento dado por el minimax
    * @param p
    * @return 
    */
    public Pair getNextMove(Problema p) {
        Minimax m = new Minimax();
        Pair move = new Pair();
        move = m.decisionMinimax(p, 2*d-1, color);
        return move;
    }
}
