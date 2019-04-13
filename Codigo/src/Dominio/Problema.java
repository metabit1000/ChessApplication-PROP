package Dominio;

import Dominio.fichas.Ficha;
import ClasesExtra.Coordenada;
import Dominio.fichas.*;
import java.util.*;
/**
 *
 * @author Àlex
 */
public final class Problema {
    private static Ficha[][] board = new Ficha[8][8];
    private Boolean turnoInicial; //blanco = true, negro = false
    
    public Problema() {}
     
    public Problema(String fen) {
        fenToMatrix(fen);
    }
     
    public Ficha getFicha(Coordenada c) {
        return board[c.getX()][c.getY()];
    }
    
    public void setFicha(Coordenada c, Ficha f) {
        board[c.getX()][c.getY()] = f;
    }
    
    public Coordenada getPosicion(Ficha f) {
        Coordenada c = new Coordenada();
        return c;
    }
    
    public Coordenada stringToCoord(String s) {
        Coordenada c = new Coordenada();
        Boolean first = true;
        if (s.length() != 2) System.out.println("No es una coordenada válida.");
        for (int i = 0; i < s.length(); i++) {
            int o;
            if (first) {
                first = false;
                switch (s.charAt(i)) {
                    case 'a':
                        o = 0;
                        c.setX(o);
                        break;
                    case 'b':
                        o = 1;
                        c.setX(o);
                        break;
                    case 'c':
                        o = 2;
                        c.setX(o);
                        break;
                    case 'd':
                        o = 3;
                        c.setX(o);
                        break;
                    case 'e':
                        o = 4;
                        c.setX(o);
                        break;
                    case 'f':
                        o = 5;
                        c.setX(o);
                        break;
                    case 'g':
                        o = 6;
                        c.setX(o);
                        break;
                    case 'h':
                        o = 7;
                        c.setX(o);
                        break;
                }
            }
            else {
                char j = s.charAt(i);
                int p = j - '0';
                int h = p+1;
                int u;
                switch (h) {
                    case 1:
                        u = 8;
                        c.setY(u);
                        break;
                    case 2:
                        u = 7;
                        c.setY(u);
                        break;
                    case 3:
                        u = 6;
                        c.setY(u);
                        break;
                    case 4:
                        u = 5;
                        c.setY(u);
                        break;
                    case 5:
                        u = 4;
                        c.setY(u);
                        break;
                    case 6:
                        u = 3;
                        c.setY(u);
                        break;
                    case 7:
                        u = 2;
                        c.setY(u);
                        break;
                    case 8:
                        u = 1;
                        c.setY(u);
                        break;
                    
                }
            }
        }
        return c;
    }
    
    public void moveFicha(String s1, String s2) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        Coordenada c1 = stringToCoord(s1);
        Coordenada c2 = stringToCoord(s2);
        Ficha f1 = getFicha(c1);
        if (f1.posiblesMovimientos(board,c2)) {
            setFicha(c2, f1);
            removeFicha(c1);
        }
       // else System.out.println("Error");
    }
    
    public void removeFicha(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        board[x][y] = null;
    }
    
    public void printTablero() {
        Ficha j;
        int count = 8;
        System.out.println("   -----------------");
        for (int x=0; x < 8; x++) {
            System.out.print(count + " |");
            for (int y=0; y < 8; y++) {
                if (board[y][x] == null) System.out.print(" ·");
                else System.out.print(" " + board[y][x].getID());
            }
            System.out.println(" |");
            
            if (x == 7) {
                System.out.println("   -----------------");
                System.out.println("    a b c d e f g h");
            }
            --count;
        }
    }
     
    public void fenToMatrix(String fen) {
        int j = 0;
        int i = 0;
        for (int m = 0; m < fen.length() && j < 8; ++m) { //"1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w"
            if (fen.charAt(m) == '1' ||fen.charAt(m) == '2' ||fen.charAt(m) == '3' || fen.charAt(m) == '4' ||fen.charAt(m) == '5' ||fen.charAt(m) == '6' ||fen.charAt(m) == '7' ||fen.charAt(m) == '8') {
                char c = fen.charAt(m);
                int p = c - '0';
                for (int n = 0; n < p; n++) {

                    board[i][j] = null;
                    ++i;

                }
            }
            else if (fen.charAt(m) == '/') {
                ++j;
                i = 0;           
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
                        Knigth N = new Knigth(true, fen.charAt(m));
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
                        Knigth n = new Knigth(false,fen.charAt(m));
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
                ++i;
            }
        }
    }
    //Cambio de Coordenada a Problema
    public Boolean esValid(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        return (x >= 0 && y <= 7 && x <= 7 && y >= 0);
    }    
    
}
