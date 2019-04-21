package ClasesExtra;

import Dominio.*;
import Dominio.fichas.Ficha;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Àlex
 */
public class MinimaxAlphaBeta {   
    
    class linea {  //necesario para guardar todo para el minimax
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
    
    public Pair<Coordenada,Coordenada> decisionMinimax(Problema p, int depth, boolean col) {
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
                System.out.println(currMove.coordToString()+" a "+movePosible.coordToString()+" valor: "+val);
                p.undoFicha(movePosible.coordToString(),currMove.coordToString(),o);
                datos.add(new linea(currMove,movePosible,val));
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
        return new Pair <> (mejor.cinicial,mejor.cfinal);
    }
    
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
                return (-1)*150 + evalQueen[y][x];
            case 'K':
                return 900 + (kingEvalWhite[y][x]);
            case 'k':
                return (-1)*900 + (kingEvalBlack[y][x]);
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
