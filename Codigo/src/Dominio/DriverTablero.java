package Dominio;

/**
 *
 * @author Àlex
 */
public class DriverTablero {
     public static void main (String [] args) {
        String fenPrueba ="1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w";
        Problema t = new Problema(fenPrueba);
        t.printTablero();
     }
}
