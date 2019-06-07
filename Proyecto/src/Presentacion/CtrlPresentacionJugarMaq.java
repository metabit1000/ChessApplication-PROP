package Presentacion;

import Dominio.CtrlPartida;

/**
 *
 * @author àlex
 */
public class CtrlPresentacionJugarMaq {
    CtrlPartida ctrlP = new CtrlPartida();
    
    public CtrlPresentacionJugarMaq(int difBlancas,int difNegras,int problInicial) {
        ctrlP = new CtrlPartida(difBlancas,difNegras,problInicial);
    }
    
    public void playNProblemas(int res[]) {
        ctrlP.playNProblemas(res);
    }
    
    public int getPuntuacionM1() {
        return ctrlP.getPuntuacionM1();
    }
    
    public int getPuntuacionM2() {
        return ctrlP.getPuntuacionM2();
    }
}
