package ClasesExtra;

import Dominio.*;
import Dominio.fichas.Ficha;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class MinimaxAlphaBeta {    
    int [][] pawnEvalWhite =
    {
        {0,  0,  0,  0,  0,  0,  0,  0},
        {50,  50,  50,  50,  50,  50,  50,  50},
        {10,  10,  20,  30,  30,  20,  10,  10},
        {5,  5,  10,  25,  25,  10,  5,  5},
        {0,  0,  0,  20,  20,  0,  0,  0},
        {5, -5, -10,  0,  0, -10, -5,  5},
        {5,  10, 10,  -20, -20,  10,  10,  5},
        {0,  0,  0,  0,  0,  0,  0,  0}
    };

    int [][] pawnEvalBlack = reverseArray(pawnEvalWhite);
    
    int [][] knightEval =
    {
        {-50, -40, -30, -30, -30, -30, -40, -50},
        {-40, -20,  0,  0,  0,  0, -20, -40},
        {-30,  0,  10,  15,  15,  10,  0, -30},
        {-30,  5,  15,  20,  20,  15,  5, -30},
        {-30,  0,  15,  20,  20,  15,  0, -30},
        {-30,  5,  10,  15,  15,  10,  5, -30},
        {-40, -20,  0,  5,  5,  0, -20, -40},
        {-50, -40, -30, -30, -30, -30, -40, -50}
    };

    int [][] bishopEvalWhite = 
    {
        { -20, -10, -10, -10, -10, -10, -10, -20},
        { -10,  0,  0,  0,  0,  0,  0, -10},
        { -10,  0,  5,  10,  10,  5,  0, -10},
        { -10,  5,  5,  10,  10,  5,  5, -10},
        { -10,  0,  10,  10,  10,  10,  00, -10},
        { -10,  10,  10,  10,  10,  10,  10, -10},
        { -10,  5,  0,  0,  0,  0,  5, -10},
        { -20, -10, -10, -10, -10, -10, -10, -20}
    };

    int [][] bishopEvalBlack = reverseArray(bishopEvalWhite);

    int [][] rookEvalWhite = 
    {
        {  0,  0,  0,  0,  0,  0,  0,  0},
        {  5,  10,  10,  10,  10,  10,  10,  5},
        { -5,  0,  0,  0,  0,  0,  0, -5},
        { -5,  0,  0,  0,  0,  0,  0, -5},
        { -5,  0,  0,  0,  0,  0,  0, -5},
        { -5,  0,  0,  0,  0,  0,  0, -5},
        { -5,  0,  0,  0,  0,  0,  0, -5},
        {  0,   0, 0,  5,  5,  0,  0,  0}
    };

    int [][] rookEvalBlack = reverseArray(rookEvalWhite);

    int [][] evalQueen = 
    {
        { -20, -10, -10, -5, -5, -10, -10, -20},
        { -10,  0,  0,  0,  0,  00,  0, -10},
        { -10,  0,  5,  5,  5,  5,  0, -10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        { -10,  5,  5,  5,  5,  5,  0, -10},
        { -10,  0,  5,  0,  0,  0,  0, -10},
        { -20, -10, -10, -5, -5, -10, -10, -20}
    };

    int [][] kingEvalWhite = 
    {
        { -30, -40, -40, -50, -50, -40, -40, -30},
        { -30, -40, -40, -50, -50, -40, -40, -30},
        { -30, -40, -40, -50, -50, -40, -40, -30},
        { -30, -40, -40, -50, -50, -40, -40, -30},
        { -20, -30, -30, -40, -40, -30, -30, -20},
        { -10, -20, -20, -20, -20, -20, -20, -10},
        {  20,  20,  0,  0,  0,  0,  20,  20},
        {  20,  30,  10,  0,  0,  10,  30,  20}
    };

    int [][] kingEvalBlack = reverseArray(kingEvalWhite);

    public MinimaxAlphaBeta() {}
    
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
                p.moveFicha(currMove.coordToString(),movePosible.coordToString());
                //if (p.checkmate(col)) return new Pair(bestCurrMove,bestMovePosible);  
                int val = col ? min(p,depth-1,-10000,10000,!col): max(p,depth-1,-10000,10000,!col);
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
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);
                //System.out.println("Valor devuelto: "+highestSeenValue+ " Origen: "+bestCurrMove.coordToString()+ " Destino: "+ bestMovePosible.coordToString());
            }
        }
        return new Pair(bestCurrMove,bestMovePosible);
    }
    
    public int min(Problema p, int depth, int alpha, int beta, boolean col) {
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
                p.moveFicha(currMove.coordToString(),movePosible.coordToString());
                int val = max(p,depth-1,alpha,beta,!col);
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);
                if (val < lowestSeenValue) lowestSeenValue = val;
                beta = Math.min(beta, val);
                if (beta <= alpha) return lowestSeenValue; //rompo poda
            }
        }
        return lowestSeenValue;
    }
    
    public int max(Problema p, int depth, int alpha, int beta, boolean col) {
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
                p.moveFicha(currMove.coordToString(),movePosible.coordToString());
                int val = min(p,depth-1,alpha,beta,!col);
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);
                if (val > highestSeenValue) highestSeenValue = val;
                alpha = Math.max(alpha,val);
                if (beta <= alpha) return highestSeenValue; //rompo poda
            }
        }
        return highestSeenValue;
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
                return 10 + pawnEvalWhite[y][x];
            case 'p':
                return (-1)*10 + pawnEvalBlack[y][x];
            case 'R':
                return 50 + rookEvalWhite[y][x];
            case 'r':
                return (-1)*50 + rookEvalBlack[y][x];
            case 'N':
                return 30 + knightEval[y][x];
            case 'n':
                return (-1)*30 + knightEval[y][x];
            case 'B':
                return 30 + bishopEvalWhite[y][x];
            case 'b':
                return (-1)*30 + bishopEvalBlack[y][x];
            case 'Q':
                return 150 + evalQueen[y][x];
            case 'q':
                return (-1)*150  + evalQueen[y][x];
            case 'K':
                return 900 + kingEvalWhite[y][x];
            case 'k':
                return (-1)*900 + kingEvalBlack[y][x];
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
    
    public int [][] reverseArray(int [][] array) {
        int xn = 8;
        int yn = 8;
        int res [][] = new int[xn][yn];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                res[xn-1][yn-1] = array[i][j];
                --yn;
            }
            yn = 8;
            --xn;
        }
        return res;
    }
}
