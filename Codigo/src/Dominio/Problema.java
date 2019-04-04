package Dominio;

import Dominio.fichas.Ficha;
import ClasesExtra.Coordenada;
/**
 *
 * @author Àlex
 */
public class Problema {
     private static Ficha[][] board = new Ficha[8][8];
     
     public Problema() {}
     
     public Problema(Ficha f1,Ficha f2) { //es para probar que las fichas se mueven bien en los drivers
         Coordenada c = f1.getPosicion();
         Coordenada c2 = f2.getPosicion();
         board[c.getX()][c.getY()] = f1;
         board[c2.getX()][c2.getY()] = f2; 
     }
     
     public Ficha getFicha(Coordenada c) {
         return board[c.getX()][c.getY()];
     }
     
    //Cambio de Coordenada a Problema
     public Boolean esValid(Coordenada c) {
        int x = c.getX();
        int y = c.getY();
        return (x >= 0 && y <= 7 && x <= 7 && y >= 0);
     }  
     
    
}
