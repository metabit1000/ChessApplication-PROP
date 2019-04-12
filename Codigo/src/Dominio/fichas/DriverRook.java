package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class DriverRook {
     public void testConstructor() {}
     public void testPosiblesMovimientos() {}
     
     public static void main (String [] args) { 
        Rook t = new Rook(true,'R');
        Rook t2 = new Rook(false,'r'); 
        Problema probl = new Problema();
        probl.setFicha(new Coordenada(1,0),t);
        probl.setFicha(new Coordenada(0,2),t2);
        ArrayList<Coordenada> res = t.posiblesMovimientos(probl, new Coordenada(1,0));
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
    }
}
