package Dominio;

import ClasesExtra.*;

/**
 *
 * @author Jordi
 */
public class MaquinaEasy extends Maquina {
    MaquinaEasy() {}
    
    MaquinaEasy(Boolean color,String nombre,int profundidad) {
        super(color,nombre,profundidad);
    }
    
    public Pair getNextMove(Problema p) {
        Minimax m = new Minimax();
        Pair move = new Pair();
        move = m.decisionMinimax(p, d, color);
        return move;
    }
}
