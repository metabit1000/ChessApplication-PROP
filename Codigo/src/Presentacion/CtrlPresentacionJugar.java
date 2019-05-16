package Presentacion;

import Dominio.CtrlPartida;

/**
 *
 * @author Àlex
 */
public class CtrlPresentacionJugar {
    CtrlPartida ctrlP = new CtrlPartida();
    
    public char[][] getTablero(int id) {
        char[][] res = ctrlP.getTablero(id);
        return res;
    }
}
