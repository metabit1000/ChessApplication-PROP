package Presentacion;

import Dominio.CtrlPartida;

/**
 *
 * @author Àlex
 */
public class CtrlPresentacionJugar {
    private CtrlPartida ctrlP = new CtrlPartida();
    
    public char[][] getTablero(int id) {
        char[][] res = ctrlP.getTablero(id);
        return res;
    }
    
    //TODA LA MIERDA DE JUGAR (EL WHILE)
}
