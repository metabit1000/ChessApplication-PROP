package Tests;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import Dominio.fichas.Reina;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class DriverReina {
    public void testConstructor() {}
    public void testPosiblesMovimientos() {}
     
    public static void main (String [] args) { 
        Reina t = new Reina(true,new Coordenada(0,0));
        Reina t2 = new Reina(false,new Coordenada(4,4)); 
        Problema probl = new Problema(t,t2);
        ArrayList<Coordenada> res = t2.posiblesMovimientos(probl);
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
    }
}
