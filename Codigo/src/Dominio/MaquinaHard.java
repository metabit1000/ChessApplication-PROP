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
    
    public Pair getNextMove(Problema p) {
        MinimaxAlphaBeta m = new MinimaxAlphaBeta();
        Pair move = new Pair();
        move = m.decisionMinimax(p, d, color);
        return move;
    }
}
