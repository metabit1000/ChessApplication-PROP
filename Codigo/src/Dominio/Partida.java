package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Ficha;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class Partida {
    private Problema p = new Problema();
    private Jugador j1 = new Jugador();
    private Jugador j2 = new Jugador();
    private double time ;
    private boolean turno  ;
    
    public Partida() {
        turno = false ; 
    }
    
    
    public void moveFicha(String s1, String s2) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        Coordenada c1 = new Coordenada();
        c1.stringToCoord(s1);
        Coordenada c2 = new Coordenada();
        c2.stringToCoord(s2);
        Ficha f1 = p.getFicha(c1);
        if (f1 != null) {
            ArrayList<Coordenada> pM = new ArrayList<>(); 
            pM = f1.posiblesMovimientos(p,c1);
            Boolean find = false;
            for (int i = 0; i < pM.size() && !find; i++) {
                Coordenada x = pM.get(i);
                if (x.getX() == c2.getX() && x.getY() == c2.getY()) find = true;
            }
            if (find) {
                p.setFicha(c2, f1);
                removeFicha(c1);
            }
            else System.out.println("La coordenada de destino no es correcta.");
        }
        else System.out.println("En la coordenada de origen no hay ficha.");
    }
    
    public void removeFicha(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        p.board[x][y] = null;
    }
  
}
