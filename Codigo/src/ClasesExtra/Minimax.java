package ClasesExtra;

import Dominio.*;
import Dominio.fichas.Ficha;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Minimax {   
    private class linea {  //"struct" creada para introducir valores para calcular el resultado del minimax
        public Coordenada cinicial;
        public Coordenada cfinal;
        public int valor;
        
        public linea() {}
        
        public linea(Coordenada c1,Coordenada c2,int valor) {
            cinicial = c1;
            cfinal = c2;
            this.valor = valor;
        }
    };
    
    public Minimax() {}
    
    /* Función para el minimax, te retorna de una posible posicion de una ficha en el tablero, el mejor 
       valor mirando sus posibles movimientos y siempre teniendo en cuenta los movimientos del contrincante. Parte recursiva. 
       Se ha optado por usar math.max y math.min para tenerlo todo en una misma función y ahorrar código que hace lo mismo*/
    public int minimax(Problema p,int depth, boolean col) {
        if (depth == 0) return evaluationBoard(p);

        ArrayList<Coordenada> moves = posiciones(p,col);
        Coordenada currMove,movePosible;
        int bestMove;
        if (col) bestMove = -9999;
        else bestMove = 9999;
        for (int i = 0; i < moves.size(); ++i) {
            currMove = moves.get(i);
            ArrayList<Coordenada> movesPosibles = p.getFicha(currMove).posiblesMovimientos(p, currMove);
            for (int x = 0; x < movesPosibles.size(); ++x) { 
                movePosible = movesPosibles.get(x);
                Ficha o = p.getFicha(movePosible);
                p.moveFicha(currMove.coordToString(),movePosible.coordToString());
                if (col) bestMove = Math.max(bestMove, minimax(p,depth-1,!col));
                else bestMove = Math.min(bestMove, minimax(p,depth-1,!col));
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);   
            }
        }
        return bestMove;
    }
    
    /* Dado un problema, una profundidad y un color para la máquina, devuelve el mejor movimiento 
       de todos sus posibles teniendo en cuenta el color */
    public PairCoordenadas decisionMinimax(Problema p, int depth, boolean col) {
  	int bestMove;
        if (col) bestMove= -9999;
        else bestMove = 9999;
        Coordenada currMove,movePosible;
        ArrayList<linea> datos = new ArrayList();
        ArrayList<Coordenada> moves = posiciones(p,col);
        for (int i = 0; i < moves.size(); ++i) {
            currMove = moves.get(i);
            ArrayList<Coordenada> movesPosibles = p.getFicha(currMove).posiblesMovimientos(p, currMove);
            for (int x = 0; x < movesPosibles.size(); ++x) {
                movePosible = movesPosibles.get(x);
                Ficha o = p.getFicha(movePosible);
                p.moveFicha(currMove.coordToString(),movePosible.coordToString());
                int val = minimax(p,depth-1,!col); //cambio al oponente
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);
                
                p.moveFicha(currMove.coordToString(),movePosible.coordToString());
                if (!p.mate(!col)) datos.add(new linea(currMove,movePosible,val));
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);
            }
        }
        linea mejor = new linea();
        for (int z = 0; z < datos.size(); ++z) {
            if (col) {
                if (datos.get(z).valor > bestMove) {
                    bestMove = datos.get(z).valor;
                    mejor = datos.get(z);
                } 
            }
            else {
                if (datos.get(z).valor < bestMove) {
                    bestMove = datos.get(z).valor;
                    mejor = datos.get(z);
                } 
            }   
        }
        return new PairCoordenadas(mejor.cinicial,mejor.cfinal);
    }
    
    /* Dado un problema, retorna la evaluación (suma de los valores de todas las fichas del tablero) en un momento dado de la partida  */
    public int evaluationBoard(Problema p) {
        int totalEvaluation = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                totalEvaluation = totalEvaluation + getPieceValue(p.getFicha(new Coordenada(i,j)),i,j);
            }
        }
        return totalEvaluation;
    }
    
    public int getPieceValue (Ficha piece,int x,int y) {
        if (piece == null) 
            return 0;
        return getAbsoluteValue(piece,x,y);
    }
    
    /* Dada una ficha del tablero, te da su valor según su tipo y color. 
       El valor se ha escogido según la importancia de la ficha en el ajedrez*/
    public int getAbsoluteValue(Ficha piece,int x,int y) {
        if (piece.getID() != null) 
            switch (piece.getID()) {
            case 'P':
                return 10;
            case 'p':
                return (-1)*10;
            case 'R':
                return 50;
            case 'r':
                return (-1)*50;
            case 'N':
                return 30;
            case 'n':
                return (-1)*30;
            case 'B':
                return 30;
            case 'b':
                return (-1)*30;
            case 'Q':
                return 150;
            case 'q':
                return (-1)*150;
            case 'K':
                return 900;
            case 'k':
                return (-1)*900;
        } 
        return -1;
    }
    
    public ArrayList<Coordenada> posiciones(Problema p, boolean color) {
        ArrayList<Coordenada> moves = new ArrayList();
        for (int i = 0; i< 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (p.getFicha(new Coordenada(i,j))!= null && p.getFicha(new Coordenada(i,j)).getColor() == color){
                    moves.add(new Coordenada(i,j));
                }
            }
        }
        return moves;
    }
}
