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
     * pre:Dado un problema queremos obtener un Pair de una posicion valida
     * post:Devolvera un Pair correcto y con el mejor movimientos entre los posibles
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
