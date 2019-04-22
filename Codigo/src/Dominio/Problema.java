package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.*;
import java.util.*;
/**
 *
 * @author Joan
 */
public final class Problema {
    private static Ficha[][] board = new Ficha[8][8];
    private Boolean turnoInicial; //blanco = true, negro = false
    private int numMovimientos;
    
    public Problema() {}
    
    public Problema(int numMov) {
        numMovimientos = numMov;
    }
    
    public Problema(String fen) {
        fenToMatrix(fen);
    }
    public Problema(Boolean turno) {
        turnoInicial=turno;
    }
    public Ficha getFicha(Coordenada c) {
        return board[c.getX()][c.getY()];
    }
    
    public void setFicha(Coordenada c, Ficha f) {
        board[c.getX()][c.getY()] = f;
    }
    
    public void setTurno(Boolean f) {
        turnoInicial = f;
    }
        
    public Boolean getTurno() {
        return turnoInicial;
    }
    
    public Coordenada getPosicion(Ficha f) {
        Coordenada c = new Coordenada();
        return c;
    }
    
    public int getNumMovimientos() {
        return numMovimientos;
    }
    
    public void setNumMovimientos(int num) {
        numMovimientos = num;
    }
    
    public void printTablero() {
        int count = 8;
        System.out.println("   -----------------");
        for (int x=0; x < 8; x++) {
            System.out.print(count + " |");
            for (int y=0; y < 8; y++) {
                if (board[x][y] == null) System.out.print(" ·");
                else System.out.print(" " + board[x][y].getID());
            }
            System.out.println(" |");
            
            if (x == 7) {
                System.out.println("   -----------------");
                System.out.println("    a b c d e f g h");
            }
            --count;
        }
    }
    
    public String matrixToFen(/*Ficha[][] f*/) {
        Ficha[][] f = board;
        String fen = "";
        for (int i = 0; i < 8; i++) {
            int count = 0;
            for (int j = 0; j < 8; j++) {
                if (f[i][j] == null && j == 7){
                    ++count;
                    String num = Integer.toString(count);
                    if (count > 0) fen += num;
                    count = 0;
                }
                else if (f[i][j] == null) ++count;
                
                else {
                    String num = Integer.toString(count);
                    if (count > 0) fen += num;
                    fen += f[i][j].getID();
                    count = 0;
                }
            }
            fen += "/";
        }
        fen += " ";
        if (turnoInicial) fen += "w";
        else fen += "b";
        return fen;
    }
     
    public void fenToMatrix(String fen) {
        int j = 0; //y
        int i = 0; //x
        for (int m = 0; m < fen.length() && i < 8; ++m) { //"1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w"
            if (fen.charAt(m) == '1' ||fen.charAt(m) == '2' ||fen.charAt(m) == '3' || fen.charAt(m) == '4' ||fen.charAt(m) == '5' ||fen.charAt(m) == '6' ||fen.charAt(m) == '7' ||fen.charAt(m) == '8') {
                char c = fen.charAt(m);
                int p = c - '0';
                for (int n = 0; n < p; n++) {

                    board[i][j] = null;
                    ++j;

                }
            }
            else if (fen.charAt(m) == '/') {
                ++i;
                j = 0;           
            }
            else if (fen.charAt(m) == ' ') {
                ++m;
                if (fen.charAt(m) == 'w' ) {
                    System.out.println("salen blancas");
                    turnoInicial = true;
                }
                else {
                    System.out.println("salen negras");
                    turnoInicial = false;
                }
            }
            else {
                switch(fen.charAt(m)) {
                    case 'R':
                        Rook R = new Rook(true, fen.charAt(m));
                        board[i][j] = R;
                        break;
                    case 'P':
                        Pawn P = new Pawn(true, fen.charAt(m));
                        board[i][j] = P;
                        break;
                    case 'N':
                        Knight N = new Knight(true, fen.charAt(m));
                        board[i][j] = N;
                        break;
                    case 'B':
                        Bishop B = new Bishop(true, fen.charAt(m));
                        board[i][j] = B;
                        break;
                    case 'Q':
                        Queen Q = new Queen(true, fen.charAt(m));
                        board[i][j] = Q;
                        break;
                    case 'K':
                        King K = new King(true, fen.charAt(m));
                        board[i][j] = K;
                        break;
                    case 'r':
                        Rook r = new Rook(false, fen.charAt(m));
                        board[i][j] = r;
                        break;
                    case 'p':
                        Pawn p = new Pawn(false, fen.charAt(m));
                        board[i][j] = p;
                        break;
                    case 'n':
                        Knight n = new Knight(false,fen.charAt(m));
                        board[i][j] = n;
                        break;
                    case 'b':
                        Bishop b = new Bishop(false,fen.charAt(m));
                        board[i][j] = b;
                        break;
                    case 'q':
                        Queen q = new Queen(false,fen.charAt(m));
                        board[i][j] = q;
                        break;
                    case 'k':
                        King k = new King(false,fen.charAt(m));
                        board[i][j] = k;
                        break;
                }
                ++j;
            }
        }
    }
    public boolean moveFicha(String s1, String s2) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        Coordenada c1 = new Coordenada();
        c1.stringToCoord(s1);
        Coordenada c2 = new Coordenada();
        c2.stringToCoord(s2);
        Ficha f1 = board[c1.getX()][c1.getY()];
        if (f1 != null) {
            ArrayList<Coordenada> pM = f1.posiblesMovimientos(this,c1);
            Boolean find = false;
            for (int i = 0; i < pM.size() && !find; i++) {
                Coordenada x = pM.get(i);
                if (x.getX() == c2.getX() && x.getY() == c2.getY()) find = true;
            }
            if (find) {
                setFicha(c2, f1);               
                removeFicha(c1);
                return true;
            }
            else {
                System.out.println("La coordenada de destino no es correcta.");
                return false;
            }
        }
        else {
            System.out.println("En la coordenada de origen no hay ficha.");
            return false;
        }
    }
    
    
    public void removeFicha(Coordenada c) {
        int i = c.getX();
        int j = c.getY();
        board[i][j] = null;
    }
    
    public boolean mate (boolean color ){
        boolean  mate = false;
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && color == board[i][j].getColor()) {
                    ArrayList<Coordenada> pM1 = board[i][j].posiblesMovimientos(this,new Coordenada(i,j));
                    for (int w = 0; w < pM1.size() ; w++) {
                        Coordenada x = pM1.get(w);
                        if (board[x.getX()][x.getY()] != null ){
                            char f = board[x.getX()][x.getY()].getID();
                            if (f == 'k' &&  color ) mate = true; 
                            if (f == 'K' && !color) mate = true;
                        }                       
                    }         
                }
            }
        }
        return mate ; 
    }
    
    public boolean checkmate(Boolean color) {
        boolean checkmate = true;
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8 ; j++) {
                Coordenada z = new Coordenada(i,j);
                if (board[i][j] != null && color != board[i][j].getColor()) {
                    ArrayList<Coordenada> pM1 = board[i][j].posiblesMovimientos(this,z);
                    for (int w = 0; w < pM1.size() ; w++) {
                        Ficha o = this.getFicha(pM1.get(w));
                        boolean u = moveFicha(z.coordToString(),pM1.get(w).coordToString());
                        if (!mate(color)) checkmate = false; 
                        undoFicha(pM1.get(w).coordToString(),z.coordToString(), o);
                    }
                }
            }
        }
        return checkmate;
    }
      

    public void undoFicha(String s1, String s2, Ficha j) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        Coordenada c1 = new Coordenada();
        c1.stringToCoord(s1);
        Coordenada c2 = new Coordenada();
        c2.stringToCoord(s2);
        Ficha f1 = getFicha(c1);
        setFicha(c2, f1);
        removeFicha(c1);
        if (j != null) {
            setFicha(c1,j);
        }
    } 
    
    //Cambio de Coordenada a Problema
    public Boolean esValid(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        return (x >= 0 && y <= 7 && x <= 7 && y >= 0);
    }    
}
    
