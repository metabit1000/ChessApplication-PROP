package Dominio.fichas;

import Dominio.Problema;
import ClasesExtra.Coordenada;
import java.util.*;

/**
 *
 * @author Àlex
 */
public class DriverKnigth {
    public void testConstructor() {}
    
    public void testPosiblesMovimientos() {}
    
    public static void main (String [] args) { 
        Caballo c = new Caballo(true,new Coordenada(3,1));
        Caballo c2 = new Caballo(false,new Coordenada(4,6)); 
        Problema probl = new Problema(c,c2);
        ArrayList<Coordenada> res = c2.posiblesMovimientos(probl);
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }

    }
}
