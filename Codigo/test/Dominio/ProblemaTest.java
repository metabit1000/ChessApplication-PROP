/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import ClasesExtra.Coordenada;
import Dominio.fichas.Ficha;
import Dominio.fichas.King;
import Dominio.fichas.Rook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jordi
 */
public class ProblemaTest {
    
    public ProblemaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFicha method, of class Problema.
     */
    @Test
    public void testGetFicha() {
        System.out.println("getFicha");
        Coordenada c = new Coordenada();
        Problema instance = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w");
        c.stringToCoord("a1");
        char result = instance.getFicha(c).getID();
        assertEquals(result, 'B');
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setFicha method, of class Problema.
     */
    @Test
    public void testSetFicha() {
        System.out.println("setFicha");
        Coordenada c = new Coordenada();
        Rook f = new Rook(true,'R');
        Problema instance = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w");
        c.stringToCoord("a3");
        instance.setFicha(c, f);
        String a = instance.matrixToFen();
         assertEquals(a, "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/R7/2N1PQ2/B6B/ w");

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setTurno method, of class Problema.
     */
    @Test
    public void testSetTurno() {
        System.out.println("setTurno");
        Boolean f = true;
        Problema instance = new Problema();
        instance.setTurno(f);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTurno method, of class Problema.
     */
    @Test
    public void testGetTurno() {
        System.out.println("getTurno");
        Problema instance = new Problema(true);
        Boolean expResult = true;
  
        Boolean result = instance.getTurno();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

   
     
 

    /**
     * Test of getNumMovimientos method, of class Problema.
     */
    @Test
    public void testGetNumMovimientos() {
        System.out.println("getNumMovimientos");
        Problema instance = new Problema();
        int expResult = 5;
        instance.setNumMovimientos(5);
        int result = instance.getNumMovimientos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of setNumMovimientos method, of class Problema.
     */
    @Test
    public void testSetNumMovimientos() {
        System.out.println("setNumMovimientos");
        int num = 5;
        Problema instance = new Problema();
        instance.setNumMovimientos(num);
        int numero = instance.getNumMovimientos();
                assertEquals(num, numero);

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of printTablero method, of class Problema.
     */
    @Test
    public void testPrintTablero() {
        System.out.println("printTablero");
        Problema instance = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w");
        instance.printTablero();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of matrixToFen method, of class Problema.
     */
    @Test
    public void testMatrixToFen() {
        System.out.println("matrixToFen");
        Problema instance = new Problema(" w");
        Rook R1 = new Rook(true ,'R');
        Rook R2 = new Rook(true ,'R');
        King k = new King(true ,'k');
        Coordenada c = new Coordenada();
        Coordenada a = new Coordenada();
        Coordenada b = new Coordenada();

        c.stringToCoord("a8");
        a.stringToCoord("c7");
        b.stringToCoord("a1");
        instance.setFicha(c, R1);
        instance.setFicha(a, R2);
        instance.setFicha(b, k);
        String expResult = "R7/2R/8/8/8/8/8/k7/ w";
        String result = instance.matrixToFen();
        assertEquals(result, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of fenToMatrix method, of class Problema.
     */
    @Test
    public void testFenToMatrix() {
        System.out.println("fenToMatrix");
                Problema instance = new Problema();
        String fen = "R7/8/8/8/8/8/8/k7 w";
        instance.fenToMatrix(fen);
        assertEquals( "R7/8/8/8/8/8/8/k7/ w", instance.matrixToFen());

    }

    /**
     * Test of moveFicha method, of class Problema.
     */
    @Test
    public void testMoveFicha() {
        System.out.println("moveFicha");
        String s1 = "a8";
        String s2 = "h8";
        Problema instance = new Problema("R7/8/8/8/8/8/8/k7 w");
        boolean expResult = true;
        boolean result = instance.moveFicha(s1, s2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of removeFicha method, of class Problema.
     */
    @Test
    public void testRemoveFicha() {
        System.out.println("removeFicha");
        Coordenada c = new Coordenada();
        Problema instance = new Problema("R7/8/8/8/8/8/8/k7 w");
        c.stringToCoord("a8");
        instance.removeFicha(c);
        int i =c.getX();
        int j =c.getY();
        assertEquals(instance.isnull(i,j), true);

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of mate method, of class Problema.
     */
    @Test
    public void testMate() {
        System.out.println("mate");
        boolean color = true;
        Problema instance = new Problema("R7/8/8/8/8/8/8/k7 w");
        boolean expResult = true;
        boolean result = instance.mate(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of checkmate method, of class Problema.
     */
    @Test
    public void testCheckmate() {
        System.out.println("checkmate");
        Boolean color = true;
        Problema instance = new Problema("3R4/7Q/2pkp3/2ppp3/8/8/8 w");
        boolean expResult = true;
        boolean result = instance.checkmate(color);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of undoFicha method, of class Problema.
     */
    @Test
    public void testUndoFicha() {
        System.out.println("undoFicha");
        String s1 = "a7";
        String s2 = "g7";
        Problema instance = new Problema("R7/8/8/8/8/8/8/k7 w");
        Coordenada c = new Coordenada();
        c.stringToCoord(s1);
         Ficha f = instance.getFicha(c);
        instance.moveFicha(s1, s2);
        instance.undoFicha(s2, s1, f);
        assertEquals("R7/8/8/8/8/8/8/k7/ w", instance.matrixToFen());

    }

    /**
     * Test of esValid method, of class Problema.
     */
    @Test
    public void testEsValid() {
        System.out.println("esValid");
        Coordenada c = new Coordenada();
        c.setX(9);
        c.setY(10);
        Problema instance = new Problema();
        Boolean expResult = false;
        Boolean result = instance.esValid(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
