

package Presentacion;

import Dominio.Problema;
import Persistencia.CtrlDatosProblemas;

/**
 * 
 * @author joan
 */
public class CtrlPresentacionProblema {
    Problema p = new Problema();
    CtrlDatosProblemas cdp = new CtrlDatosProblemas();
    
    public char[][] convertirTablero() {
        return p.convertirTablero();
    }
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
