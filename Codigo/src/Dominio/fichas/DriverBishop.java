package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class DriverBishop {
    public void testConstructor() {//que pongo?
    }
    
    public void testPosiblesMovimientos() {//que pongo?   
    }
    
    public static void main (String [] args) { 
        Alfil c = new Alfil(true,new Coordenada(3,1));
        Alfil c2 = new Alfil(false,new Coordenada(4,4)); 
        Problema probl = new Problema(c,c2);
        ArrayList<Coordenada> res = c2.posiblesMovimientos(probl);
        
        for(int x=0;x<res.size();x++) {
            res.get(x).printxy();
        }
    }
}
