package Dominio;

import Dominio.fichas.Ficha;
import ClasesExtra.Coordenada;
import Dominio.fichas.*;
import java.util.*;
/**
 *
 * @author Àlex
 */
public class Tablero {
    private static Ficha[][] board = new Ficha[8][8];
     
    public Tablero() {}
     
    public Tablero(String fen) {
        board = fenToMatrix(fen);
    }
     
    public Ficha getFicha(Coordenada c) {
        return board[c.getX()][c.getY()];
    }
    
    public void setFicha(Coordenada c, Ficha f) {
        board[c.getX()][c.getY()] = f;
    }
    
    public void moveFicha(Coordenada c1, Coordenada c2) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        
        Ficha f1 = getFicha(c1);
        f1.setPosicion(c2);
        setFicha(c2, f1);
        
    }
    
    public void printTablero() {
        System.out.println("-----------------------");
        
        System.out.println("| ");
        System.out.println("-----------------------");
        System.out.println("a b c d e f g h");
        System.out.println("a b c d e f g h");
        System.out.println("a b c d e f g h");
    }
     
    public Tablero fenToMatrix(String fen) {
        Tablero f;//C0MO LLAMO A ESTOOOOOOOOOOOOO??
        int j = 0;
        int i = 0;
        for (int m = 0; m < fen.length(); ++m) {
            if (fen.charAt(m) == '1' ||fen.charAt(m) == '2' ||fen.charAt(m) == '3' || fen.charAt(m) == '4' ||fen.charAt(m) == '5' ||fen.charAt(m) == '6' ||fen.charAt(m) == '7' ||fen.charAt(m) == '8') {
                for (int n = i; n < fen.charAt(m); n++) {
                    f[n][j] = null;
                }
                i = fen.charAt(m);
            }
            else if (fen.charAt(m) == '/') {
                ++j;
                i = 0;           
            }
            else if (fen.charAt(m) == ' ') {
                ++m;
                if (fen.charAt(m) == 'w' ) {}//TURNO DE LOS WHITES
                else {}  //TURNO DE LOS BLACKS
            }
            else {
                ++i;
                Coordenada c = new Coordenada(i,j);
                switch(fen.charAt(m)) {
                    case 'R':
                        Rook R = new Rook(true, c, fen.charAt(m));
                        f[i][j] = R;
                    case 'P':
                        Pawn P = new Pawn(true, c, fen.charAt(m));
                        f[i][j] = P;
                    case 'N':
                        Knigth N = new Knigth(true, c, fen.charAt(m));
                        f[i][j] = N;
                    case 'B':
                        Bishop B = new Bishop(true, c, fen.charAt(m));
                        f[i][j] = B;
                    case 'Q':
                        Queen Q = new Queen(true, c, fen.charAt(m));
                        f[i][j] = Q;
                    case 'K':
                        King K = new King(true, c, fen.charAt(m));
                        f[i][j] = K;
                    case 'r':
                        Rook r = new Rook(true, c, fen.charAt(m));
                        f[i][j] = r;
                    case 'p':
                        Pawn p = new Pawn(true, c, fen.charAt(m));
                        f[i][j] = p;
                    case 'n':
                        Knigth n = new Knigth(true, c, fen.charAt(m));
                        f[i][j] = n;
                    case 'b':
                        Bishop b = new Bishop(true, c, fen.charAt(m));
                        f[i][j] = b;
                    case 'q':
                        Queen q = new Queen(true, c, fen.charAt(m));
                        f[i][j] = q;
                    case 'k':
                        King k = new King(true, c, fen.charAt(m));
                        f[i][j] = k;
                }
            }
        }
        return f;
    }
    //Cambio de Coordenada a Problema
    public Boolean esValid(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        return (x >= 0 && y <= 7 && x <= 7 && y >= 0);
    }    
    
}
