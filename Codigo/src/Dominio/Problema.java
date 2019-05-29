package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.MinimaxPuro;
import Dominio.fichas.*;
import Persistencia.CtrlDatosProblemas;
import java.util.*;
/**
 *
 * @author Joan
 */
public final class Problema {
    private Ficha[][] board = new Ficha[8][8]; // a partir del fen
    private boolean turnoInicial; //blanco = true, negro = false
    private int numMovimientos;
    private Ranking rank = new Ranking(); //Ranking por problema
    private int id;
    
    
    public Problema() {}
    
    public Problema(int id,String fen, int numMov, Ranking r) {
        this.id = id;
        fenToMatrix(fen);
        numMovimientos = numMov;
        rank = r;
    }
    
    /**
     * pre:Dado una coordenada existente 
     * post: Devuelve una Ficha
     * @param c
     * @return 
     */ 
    public Ficha getFicha(Coordenada c) {
        return board[c.getX()][c.getY()];
    }
    
    /**
     * pre:Dado una coordenada y una ficha valida 
     * post:La ficha se ha introducido en el board
     * @param c
     * @param f 
     */
    public void setFicha(Coordenada c, Ficha f) {
        board[c.getX()][c.getY()] = f;
    }
    
    /**
     * pre:-
     * post:Devueelve la id del problema en questión
     * @return 
     */
    public int getId() {
        return id;
    }
    
    /**
     * pre:Dado el booleano f diferente a null
     * post:El turno inicial es f 
     * @param f 
     */
    public void setTurno(Boolean f) {
        turnoInicial = f;
    }
    
    /**
     * pre:-
     * post:Devuelve el color del turno incial
     * @return 
    */
    public boolean getTurno() {
        return turnoInicial;
    }
    
    /**
     * pre:-
     * post:Devulve el numero de movimiento en el cual podemos resolver el problema
     * @return 
     */
    public int getNumMovimientos() {
        return numMovimientos;
    }
    
    /**
     * pre:Dado un num diferente a 0 
     * post:Numero de movimiento es igual a num
     * @param num 
     */
    public void setNumMovimientos(int num) {
        numMovimientos = num;
    }
    
    /**
     * pre:-
     * post: Devolveremos el string que representa el fen 
     * @return 
     */
    public String getFen() {
        return matrixToFen();
    }
    
    /**
     * pre:-
     * post:Devolvemos el ranking del problema
     * @return 
     */
    public Ranking getRanking() {
        return rank;
    }
    
    /**
     * pre:Dado la matriz Board inicializada previamente
     * post:Devolvemos la matriz de chars
     * @return 
     */
    public char[][] convertirTablero() {
        char[][] res = new char[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] != null) res[i][j] = board[i][j].getID();
                else res[i][j] = '.';
            }
        }
        return res;
    }
    
    public void convertirMatrizFichas(char[][] c) {
        //matriz de chars a matriz de fichas y luego matriz to fen
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (c[i][j] != '.') {
                    switch(c[i][j]) {
                        case 'R':
                        Rook R = new Rook(true, 'R');
                        board[i][j] = R;
                        break;
                    case 'P':
                        Pawn P = new Pawn(true, 'P');
                        board[i][j] = P;
                        break;
                    case 'N':
                        Knight N = new Knight(true, 'N');
                        board[i][j] = N;
                        break;
                    case 'B':
                        Bishop B = new Bishop(true, 'B');
                        board[i][j] = B;
                        break;
                    case 'Q':
                        Queen Q = new Queen(true, 'Q');
                        board[i][j] = Q;
                        break;
                    case 'K':
                        King K = new King(true, 'K');
                        board[i][j] = K;
                        break;
                    case 'r':
                        Rook r = new Rook(false, 'r');
                        board[i][j] = r;
                        break;
                    case 'p':
                        Pawn p = new Pawn(false, 'p');
                        board[i][j] = p;
                        break;
                    case 'n':
                        Knight n = new Knight(false,'n');
                        board[i][j] = n;
                        break;
                    case 'b':
                        Bishop b = new Bishop(false,'b');
                        board[i][j] = b;
                        break;
                    case 'q':
                        Queen q = new Queen(false,'q');
                        board[i][j] = q;
                        break;
                    case 'k':
                        King k = new King(false,'k');
                        board[i][j] = k;
                        break;
                    }
                }
            }
        }
    }
    
    //Métodos para el ranking
    
    /**
     * pre:Dado un ranking , un nombre y un tiempo existente
     * post: Actualizas el ranking con el nombre y el tiempo
     * @param nombre
     * @param tiempo 
     */
    public void actualizarRanking(String nombre,double tiempo) {
        CtrlDatosProblemas ctrlP = new CtrlDatosProblemas();
        rank.setActualizar(nombre,tiempo);
        ctrlP.modificarRanking(id, rank); 
    }
    
    /**
     * pre:Dado un nombre diferente a null
     * post : Devolveremos true si el nom existe en el ranking y falso si no existe en el ranking
     * @param nom
     * @return 
     */
    public boolean existeix(String nom){
        if(rank.existeix(nom)) return true;
        else return false ; 
    }
    
    /**
     * pre:Ddado un  nombre diferente a null y un tiempo no negativo 
     * post:El nombre y el tiempo son añadidos correctamente al ranking
     * @param nombre
     * @param tiempo 
     */
    public void introducirElemento(String nombre,double tiempo) {
        CtrlDatosProblemas ctrlP = new CtrlDatosProblemas();
        rank.setElemento(nombre, tiempo);
        ctrlP.modificarRanking(id, rank); //actualizo en persistencia
    }
    
    public boolean validarProblema() {
        MinimaxPuro minimax = new MinimaxPuro();
        boolean res = false;
        int aux = minimax.decisionMinimax(this, 2*numMovimientos-1, turnoInicial);
        if (aux == 1) res = true;
        return res;
    }
    
    /**
     * pre:Dado la matriz Board inicializada previamente
     * post: Devuelve el fen equivalente a nuestra matriz de Fichas
     * @return 
     */
    public String matrixToFen() {
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
            if (i != 7)fen += "/";
        }
        fen += " ";
        if (turnoInicial) fen += "w";
        else fen += "b";
        return fen;
    }
    
    /**
      * pre:Dado  un string diferente a null
      * post: Convierte el fen en nuestra matriz llamada board al traducir esta notación
      * @param fen 
    */
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
                    turnoInicial = true;
                }
                else {
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
    
    /**
     * pre:Dado dos coordenadas validas
     * post: La ficha en la coordenada c1 se movera a la coordenada c2 si es un moviemiento valido
     * @param c1
     * @param c2
     * @return 
     */
    public boolean moveFicha(Coordenada c1, Coordenada c2) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
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
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * pre:Dada una coordanada c valida 
     * post:Eliminaremos la ficha de nuestro board en la coordenada c 
     * @param c 
     */
    public void removeFicha(Coordenada c) {
        int i = c.getX();
        int j = c.getY();
        board[i][j] = null;
    }
    
    /**
     * pre:Dado un booleano color diferente a null
     * post : Devolvera true si existe un jaque en nuestro board por parte del color que represanta el boleano
     * @param color
     * @return 
     */
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
    
    /**
     * pre:Dado un boleano color diferente a null
     * post : Devolver true si el color esta haciendo jaque mate sino devolvera false 
     * @param color
     * @return 
     */
    public boolean checkmate(Boolean color) {
        boolean checkmate = true;
        for (int i = 0; i < 8 ; i++) {
            for (int j = 0; j < 8 ; j++) {
                Coordenada z = new Coordenada(i,j);
                if (board[i][j] != null && color != board[i][j].getColor()) {
                    ArrayList<Coordenada> pM1 = board[i][j].posiblesMovimientos(this,z);
                    for (int w = 0; w < pM1.size() ; w++) {
                        Ficha o = this.getFicha(pM1.get(w));
                        boolean u = moveFicha(z,pM1.get(w));
                        if (!mate(color)) checkmate = false; 
                        undoFicha(pM1.get(w),z, o);
                    }
                }
            }
        }
        return checkmate;
    }
      
    /**
     * pre:Dados dos coordenadas validas y una ficha diferente no null
     * post: Devolveremos a la Ficha j a la coordenada c1 donde estaba originalmente ya que deshacemos el movimiento anterior 
     * @param c1
     * @param c2
     * @param j 
     */
    public void undoFicha(Coordenada c1, Coordenada c2, Ficha j) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        Ficha f1 = getFicha(c1);
        setFicha(c2, f1);
        removeFicha(c1);
        if (j != null) {
            setFicha(c1,j);
        }
    } 
    
    /**
     * pre : Dado una Coordenada diferente a null
     * post: Devolveremos true si es una coordenada valida sino devolveremos fals
     * @param c
     * @return 
     */
    public Boolean esValid(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        return (x >= 0 && y <= 7 && x <= 7 && y >= 0);
    }    
}
    
