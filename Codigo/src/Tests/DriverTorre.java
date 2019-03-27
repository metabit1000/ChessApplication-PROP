package Tests;

import ClasesExtra.Coordenada;
import Dominio.fichas.Torre;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class DriverTorre {
     public void testConstructor() {}
     public void testPosiblesMovimientos() {}
     
     public static void main (String [] args) { 
        Torre t = new Torre(true,new Coordenada(0,0));
        Torre t2 = new Torre(false,new Coordenada(1,0)); 
        Problema probl = new Problema(t,t2);
        ArrayList<Coordenada> res = t.posiblesMovimientos(probl);
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
     }
}
