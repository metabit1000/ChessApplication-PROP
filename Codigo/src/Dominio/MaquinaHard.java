package Dominio;

import ClasesExtra.MinimaxAlphaBeta;
import ClasesExtra.Pair;

/**
 *
 * @author Jordi
 */
public class MaquinaHard extends Maquina {
    MaquinaHard() {}
    
    MaquinaHard(Boolean color,String nombre,int profundidad) {
        super(color,nombre,profundidad);
    }
    
    public Pair getNextMove(Problema p) {
        MinimaxAlphaBeta m = new MinimaxAlphaBeta();
        Pair move = new Pair();
        move = m.decisionMinimax(p, d, color);
        return move;
    }
}
