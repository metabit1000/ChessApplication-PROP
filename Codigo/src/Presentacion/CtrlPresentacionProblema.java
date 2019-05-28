

package Presentacion;

import Dominio.CtrlProblemas;
import Persistencia.CtrlDatosProblemas;

/**
 * 
 * @author joan
 */
public class CtrlPresentacionProblema {
    CtrlProblemas pp= new CtrlProblemas();
    public boolean Validar(int id,String fen, int numMov) {
        
        
         return pp.Validar(id, fen, numMov);
    }
    
    public char[][] convertirTablero() {
        return pp.convertirTablero();    }
    public char[][] obtenerYconvertirTablero(int id) {
        
         return pp.obtenerYconvertirTablero(id);
    }
    public String dameFEN(char[][] c) {
       	
         return pp.dameFEN(c);
      
    }
    public int getidmodif(String nombre , int i ){
      return   pp.getIdCreado(i, nombre);
    }
   public void  introducirProblema(String fen , int numMov){
       pp.introducirProblemanuevo(numMov, fen);
   }
   public void  modificarProblema(char[][]c , int numMov,int id , String nom ){
       String fen = dameFEN(c);
       pp.modificarProblema(numMov, fen, nom, id);
   }
   public void introducirProblemaCreado(String nom){
       pp.introducirProblemaCreado(nom);
   }
    public int getAllProblemasJuegoSize() {
       return pp.getAllProblemasJuegoSize();
    }
}
