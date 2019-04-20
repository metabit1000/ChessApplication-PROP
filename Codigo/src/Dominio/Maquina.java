package Dominio;

import ClasesExtra.*;
import Dominio.Problema;
import javafx.util.Pair;

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
    
    public Pair<Coordenada,Coordenada> getNextMove(Problema p) {
        Pair<Coordenada,Coordenada> moves = new Pair<>(null,null);
        if(dificultad == 1) moves = minimax1.decisionMinimax(p,3,color); //profundidad 3
        //else if (dificultad == 2) minimax2.decisionMinimax(p); //alphaBeta...siguiente entrega
        return moves;
    }
}
