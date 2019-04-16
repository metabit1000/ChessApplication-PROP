package ClasesExtra;

import Dominio.Problema;

/**
 *
 * @author Àlex
 */
public class Minimax {
    int maxDepth;
    static int winVal = 1000000;
    static int loseVal = -1000000;
    
    public Minimax(int maxDepth) {
        this.maxDepth = maxDepth;
    }
    
    public void minValue() {
    
    }
    
    public void maxValue() {
    
    }
    
    public Coordenada decisionMinimax(Problema p) {
        Coordenada move = new Coordenada();
        return move;
    }
}
