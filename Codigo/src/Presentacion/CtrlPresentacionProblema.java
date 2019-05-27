

package Presentacion;

import Dominio.CtrlProblemas;
import Dominio.Problema;
import Dominio.Ranking;
import Persistencia.CtrlDatosProblemas;

/**
 * 
 * @author joan
 */
public class CtrlPresentacionProblema {
    Problema p = new Problema();	        
    CtrlDatosProblemas cdp = new CtrlDatosProblemas();
    
    public boolean Validar(int id,String fen, int numMov, Ranking r) {
        Problema c = new Problema(id,fen,numMov,r);	
         return c.validarProblema();
    }
    
    public char[][] convertirTablero() {
        return p.convertirTablero();    }
    public char[][] obtenerYconvertirTablero(int id) {
        
        Problema p = cdp.obtenerProblema(id);	
         return p.convertirTablero();
    }
    public String dameFEN(char[][] c) {
        p.convertirMatrizFichas(c);	        
        p.setTurno(true);	
         return p.matrixToFen();
      
    }
}
