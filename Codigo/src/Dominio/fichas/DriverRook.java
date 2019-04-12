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
        Rook t = new Rook(true,'T');
        Rook t2 = new Rook(false,'t'); 
        Problema probl = new Problema(t,t2);
        ArrayList<Coordenada> res = t.posiblesMovimientos(probl, new Coordenada(1,0));
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
    }
}
