package Dominio;
import ClasesExtra.Coordenada;
import Dominio.fichas.Bishop;
import Dominio.fichas.King;
import Dominio.fichas.Knight;
import Dominio.fichas.Pawn;
import Dominio.fichas.Queen;
import Dominio.fichas.Rook;
import java.util.*;

/**
 *
 * @author jota
 */
public class CtrlProblema {
    private Map<String, String> Problems = new HashMap<>();
    private static int numr = 2;
    private static int numR = 2;
    private static int numb = 2;
    private static int numB = 2;
    private static int numn = 2;
    private static int numN = 2;
    private static int nump = 8;
    private static int numP = 8;
    private static int numk = 1;
    private static int numK = 1;
    private static int numq = 1;
    private static int numQ = 1;
    
    public CtrlProblema() {
            Problems.put("Problema 1. Mate en 2.", "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w");
            Problems.put("Problema 2. Mate en 3.", "3K4/8/8/p2k4/pp1B4/N5N1/P2Q4/8 w");
            Problems.put("Problema 3. Mate en 3.", "1rb4r/p1q2pnk/4pBpp/2p1P3/2P2QP1/3BR3/P4P1P/3R2K1 w");
            Problems.put("Inicial", "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w");
            
    }
    
    public void addProblema(String id, String fen) {
        if (!existProblem(id)) {
            if (!existFEN(fen)) {
                Problems.put(id, fen);
            }
            else System.out.println("Este problema ya está registrado.");
        }
        else System.out.println("El ID ya está siendo utilizado. Pruebe con otro.");
    }
    
    public boolean existProblem(String id) {
        return Problems.containsKey(id);
    }
    
    public boolean existFEN(String fen) {
        return Problems.containsValue(fen);
    }
    
    public boolean cumpleRestriccionFichas(Problema crear) {
        int r = 0; 
        int n = 0; 
        int k = 0;
        int p = 0;
        int q = 0;
        int b = 0;
        int R = 0;
        int N = 0;
        int K = 0;
        int P = 0;
        int Q = 0;
        int B = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (crear.getFicha(new Coordenada(i,j)) != null) {
                    char c = crear.getFicha(new Coordenada(i,j)).getID();
                    switch(c) {
                        case 'r':
                            ++r;
                            if (r > numr) {
                                System.out.println("No puede haber más de 2 torres negras");
                                return false;
                            }
                            break;
                        case 'R':
                            ++R;
                            if (R > numR) {
                                System.out.println("No puede haber más de 2 torres blancas");
                                return false;
                            }
                            break;
                        case 'q':
                            ++q;
                            if (q > numq) {
                                System.out.println("No puede haber más de 1 reina negra");
                                return false;
                            }
                            break;
                        case 'Q':
                            ++Q;
                            if (Q > numQ) {
                                System.out.println("No puede haber más de 1 reina blanca");
                                return false;
                            }
                            break;
                        case 'b':
                            ++b;
                            if (b > numb) {
                                System.out.println("No puede haber más de 2 alfiles negros");
                                return false;
                            }
                            break;
                        case 'B':
                            ++B;
                            if (B > numB) {
                                System.out.println("No puede haber más de 2 alfiles blancos");
                                return false;
                            }
                            break;
                        case 'n':
                            ++n;
                            if (n > numn) {
                                System.out.println("No puede haber más de 2 caballos negros");
                                return false;
                            }
                            break;
                        case 'N':
                            ++N;
                            if (N > numN) {
                                System.out.println("No puede haber más de 2 caballos blancos");
                                return false;
                            }
                            break;
                        case 'p':
                            ++p;
                            if (p > nump) {
                                System.out.println("No puede haber más de 8 peones negros");
                                return false;
                            }
                            break;
                        case 'P':
                            ++P;
                            if (P > numP) {
                                System.out.println("No puede haber más de 8 peones blancos");
                                return false;
                            }
                            break;
                        case 'k':
                            ++k;
                            break;
                        case 'K':
                            ++K;
                            
                            break;
                    }
                    if (K != numK) {
                                System.out.println("Tiene que haber 1 rey blanco");
                                return false;
                            }
                    if (k != numk) {
                        System.out.println("Tiene que haber 1 rey negro");
                        return false;
                    }
                }
            }
            
        }
        return true;
    }
    
    public void anadirFicha(Problema crear, String ficha, String color, String pos) {
        switch(ficha) {
            case "r":
                Rook r;
                if (color.equals("n")) {
                    r = new Rook(false, 'r');
                }
                else if (color.equals("b")) {
                    r = new Rook(true, 'R');
                }
                else {
                    System.out.println("Error, vuelva a intentarlo");
                    break;
                }
                Coordenada coordR = new Coordenada();
                coordR.stringToCoord(pos);
                try {
                    crear.setFicha(coordR,r);
                    crear.printTablero();
                } catch (ArrayIndexOutOfBoundsException e2){
                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    break;
                }
                break;
            case "k":
                King k;
                if (color.equals("n")) {
                    k = new King(false, 'k');
                   // hayReyNegro = true;
                }
                else if (color.equals("b")) {
                    k = new King(true, 'K');
                  //  hayReyBlanco = true;
                }
                else {
                    System.out.println("Error, vuelva a intentarlo");
                    break;
                }
                Coordenada coordK = new Coordenada();
                coordK.stringToCoord(pos);
                try {
                    crear.setFicha(coordK,k);
                } catch (ArrayIndexOutOfBoundsException e2){
                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    break;
                }
                break;
            case "q":
                Queen q;
                if (color.equals("n")) {
                    q = new Queen(false, 'q');
                }
                else if (color.equals("b")) {
                    q = new Queen(true, 'Q');
                }
                else {
                    System.out.println("Error, vuelva a intentarlo");
                    break;
                }
                Coordenada coordQ = new Coordenada();
                coordQ.stringToCoord(pos);
                try {
                    crear.setFicha(coordQ,q);
                } catch (ArrayIndexOutOfBoundsException e2){
                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    break;
                }
                break;
            case "p":
                Pawn p;
                if (color.equals("n")) {
                    p = new Pawn(false, 'p');
                }
                else if (color.equals("b")) {
                    p = new Pawn(true, 'P');
                }
                else {
                    System.out.println("Error, vuelva a intentarlo");
                    break;
                }
                Coordenada coordP = new Coordenada();
                coordP.stringToCoord(pos);
                try {
                    crear.setFicha(coordP,p);
                } catch (ArrayIndexOutOfBoundsException e2){
                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    break;
                }
                break;
            case "b":
                Bishop b;
                if (color.equals("n")) {
                    b = new Bishop(false, 'b');
                }
                else if (color.equals("b")) {
                    b = new Bishop(true, 'B');
                }
                else {
                    System.out.println("Error, vuelva a intentarlo");
                    break;
                }
                Coordenada coordB = new Coordenada();
                coordB.stringToCoord(pos);
                try {
                    crear.setFicha(coordB,b);
                } catch (ArrayIndexOutOfBoundsException e2){
                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    break;
                }
                break;
            case "n":
                Knight n;
                if (color.equals("n")) {
                    n = new Knight(false, 'n');
                }
                else if (color.equals("b")) {
                    n = new Knight(true, 'N');
                }
                else {
                    System.out.println("Error, vuelva a intentarlo");
                    break;
                }
                Coordenada coordN = new Coordenada();
                coordN.stringToCoord(pos);
                try {
                    crear.setFicha(coordN,n);
                } catch (ArrayIndexOutOfBoundsException e2){
                    System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    break;
                }
                break;
        }
    }
    
    public String seleccionProblema(int n) {
        int index = 1;
        String fen = "";
        for(String key : Problems.keySet()) {
            if (n == index) fen = Problems.get(key);
            ++index;
        }
        return fen;
    }
    
    public void printProblemas() {
        int index = 1;
        for(String key : Problems.keySet()) {
            System.out.println(index + ". " +key);
            String fen = Problems.get(key);
            Problema p = new Problema();
            p.fenToMatrix(fen);
            p.printTablero();
            ++index;
        }
    }
}
