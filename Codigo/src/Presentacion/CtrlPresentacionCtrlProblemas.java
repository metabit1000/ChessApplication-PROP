package Presentacion;

import Dominio.CtrlProblemas;
import Dominio.Problema;
import java.util.ArrayList;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

public class CtrlPresentacionCtrlProblemas {
    CtrlProblemas cp = new CtrlProblemas();
    
    public int getRestricciones(char[][] c) {
        return cp.cumpleRestriccionFichas(c);
    }
    
    public ArrayList<Problema> getAllProblemasJuego() {
        return cp.getAllProblemasJuego();
    }
    
}
