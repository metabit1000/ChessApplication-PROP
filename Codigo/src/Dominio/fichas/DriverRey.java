package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class DriverRey {
    public void testConstructor() {}
    
    public void testPosiblesMovimientos() {}
    
    public static void main (String [] args) { 
        Rey r = new Rey(true,new Coordenada(3,1));
        Rey r2 = new Rey(false,new Coordenada(2,2)); 
        Problema probl = new Problema(r,r2);
        ArrayList<Coordenada> res = r.posiblesMovimientos(probl);
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
    }

}
