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
    
   public int getAllProblemasJuegoSize() {
       return cp.getAllProblemasJuegoSize();
    }
   public int getId(int i ){
      return  cp.getId(i);
       
   }
    public int getnumMov(int i ){
      return  cp.getnumMov(i);
   } 
}
