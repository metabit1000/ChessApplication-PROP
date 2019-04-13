package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Ficha;

/**
 *
 * @author 
 */
public class Partida {
    private Problema p = new Problema();
    //Jugador1
    //Jugador2
    //tiempo
    //turno
    
    public Partida() {}
    
    public void moveFicha(String s1, String s2) {
        //dadas dos posiciones, mueve la ficha de coord c1 a c2 siempre y cuando c2 se pueda acceder,
        //no haya una ficha de igual color a la que movemos y este dentro del tablero. Si hay una ficha rival 
        //en c2, nos la comemos
        Coordenada c1 = p.stringToCoord(s1);
        Coordenada c2 = p.stringToCoord(s2);
        Ficha f1 = p.getFicha(c1);
        p.removeFicha(c1);
        //}
       // else System.out.println("Error");
    }
}
