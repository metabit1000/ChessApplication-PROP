package Presentacion;

import Dominio.CtrlProblemas;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

public class CtrlPresentacionCtrlProblemas {
    CtrlProblemas cp = new CtrlProblemas();
    
    public int getRestricciones(char[][] c) {
        return cp.cumpleRestriccionFichas(c);
    }
    
}
