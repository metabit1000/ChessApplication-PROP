package Dominio;

import ClasesExtra.*;
import Dominio.Problema;

/**
 *
 * @author Àlex
 */
public class Maquina extends Jugador {
    int dificultad; //1 facil, 2 dificil
    MinimaxV2 minimax1 = new MinimaxV2();
    //MinimaxAlphaBeta minimax2;
    
    public Maquina() {}
    
    public Maquina(Boolean color,int dificultad) {
        super(color);
        this.dificultad = dificultad;
    }
    
    public Coordenada getNextMove(Problema p) {
        Coordenada move = new Coordenada();
        if(dificultad == 1) move = minimax1.decisionMinimax(p,3,color); //profundidad 3
        //else if (dificultad == 2) minimax2.decisionMinimax(p); //alphaBeta...siguiente entrega
        return move;
    }
}
