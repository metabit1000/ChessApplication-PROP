
package Dominio;

/**
 *
 * @author Jordi
 */
public class DriverPruebaMinimaxPuro {
    public static void main(String[]args){
        
        Problema p = new Problema(40,"8/8/8/8/1n6/8/3QbkNK/4N3 w",2,null);
        if (p.validarProblema()) System.out.println("Funciona");
        else System.out.println("No hace una mierda");
    }
}
