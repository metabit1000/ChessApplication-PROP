package ClasesExtra;

import Dominio.*;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Minimax {
    int maxDepth;
    int IDdepth;
    int tempDepth;
    Boolean player;
    static int winVal = 1000000;
    static int loseVal = -1000000;
    
    protected static int [] pawnTable = {
          0,  0,  0,  0,  0,  0,  0,  0,
          50, 50, 50, 50, 50, 50, 50, 50,
          10, 10, 20, 30, 30, 20, 10, 10,
          5,  5, 10, 25, 25, 10,  5,  5,
          0,  0,  0, 20, 20,  0,  0,  0,
          5, -5,-10,  0,  0,-10, -5,  5,
          5, 10, 10,-20,-20, 10, 10,  5,
          0,  0,  0,  0,  0,  0,  0,  0 };

        // Placement Precedence for all Knights
      protected static int [] knightTable = {
          -50,-40,-30,-30,-30,-30,-40,-50,
          -40,-20,  0,  0,  0,  0,-20,-40,
          -30,  0, 10, 15, 15, 10,  0,-30,
          -30,  5, 15, 20, 20, 15,  5,-30,
          -30,  0, 15, 20, 20, 15,  0,-30,
          -30,  5, 10, 15, 15, 10,  5,-30,
          -40,-20,  0,  5,  5,  0,-20,-40,
          -50,-40,-30,-30,-30,-30,-40,-50 };

      protected static int [] bishopPrecedence = {
          -20,-10,-10,-10,-10,-10,-10,-20,
          -10,  0,  0,  0,  0,  0,  0,-10,
          -10,  0,  5, 10, 10,  5,  0,-10,
          -10,  5,  5, 10, 10,  5,  5,-10,
          -10,  0, 10, 10, 10, 10,  0,-10,
          -10, 10, 10, 10, 10, 10, 10,-10,
          -10,  5,  0,  0,  0,  0,  5,-10,
          -20,-10,-10,-10,-10,-10,-10,-20 };

      protected static int [] rookTable = {
            0,  0,  0,  0,  0,  0,  0,  0,
            5, 10, 10, 10, 10, 10, 10,  5,
           -5,  0,  0,  0,  0,  0,  0, -5,
           -5,  0,  0,  0,  0,  0,  0, -5,
           -5,  0,  0,  0,  0,  0,  0, -5,
           -5,  0,  0,  0,  0,  0,  0, -5,
           -5,  0,  0,  0,  0,  0,  0, -5,
            0,  0,  0,  5,  5,  0,  0,  0 };
        
      protected static int [] queenTable = {
          -20,-10,-10, -5, -5,-10,-10,-20,
          -10,  0,  0,  0,  0,  0,  0,-10,
          -10,  0,  5,  5,  5,  5,  0,-10,
           -5,  0,  5,  5,  5,  5,  0, -5,
            0,  0,  5,  5,  5,  5,  0, -5,
          -10,  5,  5,  5,  5,  5,  0,-10,
          -10,  0,  5,  0,  0,  0,  0,-10,
          -20,-10,-10, -5, -5,-10,-10,-20 };

      protected static int [] kingTable = {
          -30,-40,-40,-50,-50,-40,-40,-30,
          -30,-40,-40,-50,-50,-40,-40,-30,
          -30,-40,-40,-50,-50,-40,-40,-30,
          -30,-40,-40,-50,-50,-40,-40,-30,
          -20,-30,-30,-40,-40,-30,-30,-20,
          -10,-20,-20,-20,-20,-20,-20,-10,
           20, 20,  0,  0,  0,  0, 20, 20,
           20, 30, 10,  0,  0, 10, 30, 20 };
    
    public Minimax(int maxDepth, Boolean player) {
        this.maxDepth = maxDepth;
        this.player = player;
    }
    
    public int minValue(Problema p,Coordenada c, int currDepth) {
        if (p.getFicha(c) != null) {
            if (cutOffTest(p,c,currDepth)) {
                return utility(p,c,currDepth);
            }
        }
        int util = winVal;
        int curr;
        Coordenada currMove;
        if (p.getFicha(c) != null) {
            ArrayList<Coordenada> moves = p.getFicha(c).posiblesMovimientos(p,c);
            for (int i = 0; i < moves.size(); i++) {
                currMove = moves.get(i);
                p.moveFicha(c.coordToString(),currMove.coordToString());
                curr = maxValue(p,c,currDepth);
                p.undoMove();
                if (curr < util) 
                    util = curr;
            }
        }
        return util;
    }
    
    public int maxValue(Problema p,Coordenada c, int currDepth) {
        if (p.getFicha(c) != null) {
            if (cutOffTest(p,c,currDepth)) {
                return utility(p,c,currDepth);
            }
        }
        
        int util = loseVal;
        int curr;
        Coordenada currMove;
        ArrayList<Coordenada> moves = p.getFicha(c).posiblesMovimientos(p,c);
        for (int i = 0; i < moves.size(); i++) {
            currMove = moves.get(i);
            p.moveFicha(c.coordToString(),currMove.coordToString());
            curr = minValue(p,c,currDepth+1);
            p.undoMove();
            if (curr > util) 
                util = curr;
        }
        return util;
    }
    
    public Boolean cutOffTest(Problema p,Coordenada c, int currDepth) {
        return (p.mate(p.getFicha(c).getColor()) || currDepth > this.tempDepth);
    }
    
    public int utility(Problema p, Coordenada c, int d) {        
        if (p.mate(p.getFicha(c).getColor())) {
            if (p.getFicha(c).getColor() == this.player) return loseVal;
            else {
                if (d <= this.tempDepth) {
                    this.tempDepth = d;
                    return winVal;
                }
                else return loseVal;
            } 
        }
        else return getMaterials(p,c);  
    }
    
    public int getMaterials(Problema p, Coordenada c) {
        char currStone;// = p.getFicha(c).getID();
        int val = 0;
        int numr = 0;
        int numn = 0;
        int numq = 0;
        int numk = 0;
        int nump = 0;
        int numb = 0;
        int numR = 0;
        int numN = 0;
        int numQ = 0;
        int numK = 0;
        int numP = 0;
        int numB = 0;
        
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; j++) {
                if (p.getFicha(new Coordenada(i,j)) != null) {
                    currStone = p.getFicha(new Coordenada(i,j)).getID();
                    switch(currStone) {
                        case 'P':
                            ++numP;
                            val += 100;
                            break;
                        case 'p':
                            ++nump;
                            val -= 100;
                            break;
                        case 'R':
                            ++numR;
                            val += 500;
                            break;
                        case 'r':
                            ++numr;
                            val -= 500;
                            break;
                        case 'B':
                            ++numB;
                            val += 330;
                            break;
                        case 'b':
                            ++numb;
                            val -= 330;
                            break;
                        case 'N':
                            ++numN;
                            val += 320;
                            break;
                        case 'n':
                            ++numn;
                            val -= 320;
                            break;
                        case 'Q':
                            ++numQ;
                            val += 900;
                            break;
                        case 'q':
                            ++numq;
                            val -= 900;
                            break;
                        case 'K':
                            ++numK;
                            val += 20000;
                            break;
                        case 'k':
                            ++numk;
                            val -= 20000;
                            break;
                    }
            } 
        }
        }
        if (p.getFicha(c).getColor()) return val;
        else return -val;
    }
    
    public Coordenada decisionMinimax(Problema p,Coordenada c) {
        if (p.getFicha(c) != null) {
        for (IDdepth = 1; IDdepth < maxDepth; IDdepth++) {
            ArrayList<Coordenada> moves = p.getFicha(c).posiblesMovimientos(p,c);
            int currUtil = loseVal;
            int bestUtil = loseVal;
            int bestId = 0;
            Coordenada currMove;
            
            this.tempDepth = this.IDdepth;
            for (int i = 0; i < moves.size(); ++i) {
                currMove = moves.get(i);
                p.moveFicha(c.coordToString(),currMove.coordToString());
                currUtil = minValue(p,c,1);
                p.undoMove();
                if (bestUtil <= currUtil) {
                    bestUtil = currUtil;
                    bestId = i;
                }
            }
            
            if (bestUtil == winVal) {
                return moves.get(bestId);
            }
                
            if ((IDdepth+1) == maxDepth) {
                return moves.get(bestId);
            }
        }
        }
        Coordenada fail = new Coordenada();
        fail.setX(-1);
        fail.setY(-1);
        return fail;
    }
}
