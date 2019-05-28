package Dominio;

import ClasesExtra.MinimaxAlphaBeta;
import ClasesExtra.Pair;

/**
 *
 * @author Jordi
 */
public class MaquinaHard extends Maquina {
    MaquinaHard() {}
    
    MaquinaHard(Boolean color,int profundidad) {
        super(color,profundidad);
    }
     /**
     * pre:Dado un problema existente
     * post:Devolvera un Pair correcto y con el mejor movimientos entre los posibles
     * @param p
     * @return 
     */
    public Pair getNextMove(Problema p) {
        MinimaxAlphaBeta m = new MinimaxAlphaBeta();
        Pair move = new Pair();
        move = m.decisionMinimax(p, 2*d-1, color);
        return move;
    }
}
