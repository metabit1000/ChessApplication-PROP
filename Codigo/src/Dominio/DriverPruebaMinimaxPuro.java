
package Dominio;

/**
 *
 * @author Jordi
 */
public class DriverPruebaMinimaxPuro {
    public static void main(String[]args){
        Problema p = new Problema(2,"6k1/p1p2rpp/1q6/2p5/4P3/PQ6/1P4PP/3R3K",1,null);
        if (p.validarProblema()) System.out.println("Funciona");
        else System.out.println("No hace una mierda");
    }
}
