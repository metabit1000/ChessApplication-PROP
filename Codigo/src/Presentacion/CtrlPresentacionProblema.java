

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
        CtrlProblemas cp = new CtrlProblemas();

    
    public boolean Validar(int id,String fen, int numMov) {
        
        return cp.validar(id,fen,numMov);
    }
    
    public char[][] convertirTablero() {
        return cp.convertirTablero();
    }
    public char[][] obtenerYconvertirTablero(int id) {
        
        return cp.obtenerYconvertirTablero(id);
    }
    public String dameFEN(char[][] c) {
        return cp.dameFEN(c);
      
    }
}
