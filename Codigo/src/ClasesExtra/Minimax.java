package ClasesExtra;

import Dominio.*;
import Dominio.fichas.Ficha;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Minimax {   
    public Minimax() {}
   
    public int min(Problema p, int depth, boolean col) {
        if (depth == 0) return evaluationBoard(p);
        
        ArrayList<Coordenada> moves = posiciones(p,col);
        Coordenada currMove,movePosible;
        int lowestSeenValue = 9999;
        for (int i = 0; i < moves.size(); ++i) {
            currMove = moves.get(i);
            ArrayList<Coordenada> movesPosibles = p.getFicha(currMove).posiblesMovimientos(p, currMove);
            for (int x = 0; x < movesPosibles.size(); ++x) { 
                movePosible = movesPosibles.get(x);
                Ficha o = p.getFicha(movePosible);
                p.moveFicha(currMove,movePosible);
                int val = max(p,depth-1,!col);
                p.undoFicha(movePosible,currMove,o);
                if (val < lowestSeenValue) lowestSeenValue = val;
            }
        }
        return lowestSeenValue;
    }
    
    public int max(Problema p, int depth, boolean col) {
        if (depth == 0) return evaluationBoard(p);
        
        ArrayList<Coordenada> moves = posiciones(p,col);
        Coordenada currMove,movePosible;
        int highestSeenValue = -9999;
        for (int i = 0; i < moves.size(); ++i) {
            currMove = moves.get(i);
            ArrayList<Coordenada> movesPosibles = p.getFicha(currMove).posiblesMovimientos(p, currMove);
            for (int x = 0; x < movesPosibles.size(); ++x) { 
                movePosible = movesPosibles.get(x);
                Ficha o = p.getFicha(movePosible);
                p.moveFicha(currMove,movePosible);
                int val = min(p,depth-1,!col);
                p.undoFicha(movePosible,currMove,o);
                if (val > highestSeenValue) highestSeenValue = val;
            }
        }
        return highestSeenValue;
    }
    
    /* Dado un problema, una profundidad y un color para la máquina, devuelve el mejor movimiento 
       de todos sus posibles teniendo en cuenta el color */
    public Pair decisionMinimax(Problema p, int depth, boolean col) {
        int highestSeenValue = -9999;
        int lowestSeenValue = 9999;
        Coordenada currMove,movePosible;
        Coordenada bestCurrMove = new Coordenada(), bestMovePosible = new Coordenada();
        ArrayList<Coordenada> moves = posiciones(p,col);
        for (int i = 0; i < moves.size(); ++i) {
            currMove = moves.get(i);
            ArrayList<Coordenada> movesPosibles = p.getFicha(currMove).posiblesMovimientos(p, currMove);
            for (int x = 0; x < movesPosibles.size(); ++x) {
                movePosible = movesPosibles.get(x);
                Ficha o = p.getFicha(movePosible);
                p.moveFicha(currMove,movePosible);
                if (p.checkmate(col)) return new Pair(currMove,movePosible);  //caso en que encuentre un jaquemate en uno de los posibles movimientos
                int val = col ? min(p,depth-1,!col): max(p,depth-1,!col);
                if (!p.mate(!col)) {
                    if (col & val > highestSeenValue) {
                        highestSeenValue = val;
                        bestCurrMove = currMove; //coordenada origin
                        bestMovePosible = movePosible; //coordenada destino
                    } else if (!col & val < lowestSeenValue) {
                        lowestSeenValue = val;
                        bestCurrMove = currMove; 
                        bestMovePosible = movePosible; 
                    }
                }
                p.undoFicha(movePosible,currMove,o);
                System.out.println("Valor devuelto: "+highestSeenValue+ " Origen: "+bestCurrMove.coordToString()+ " Destino: "+ bestMovePosible.coordToString());
            }
        }
        return new Pair(bestCurrMove,bestMovePosible);
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
    
    /* Retorna las posiciones de las fichas del mismo color de todo un tablero */
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
