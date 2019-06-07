package Dominio;

import ClasesExtra.*;

/**
 *
 * @author Àlex
 */
public abstract class Maquina extends Jugador {
    int d;
    
    public Maquina() {}
    
    public Maquina(Boolean color,int profundidad) {
        super(color);
        d = profundidad;
    }
    
    /**
   * pre: Problema no nulo
   * post: Devuelve el mejor movimiento dado por el minimax
   * @param p
   * @return 
   */
    public abstract Pair getNextMove(Problema p);
}
