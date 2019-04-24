package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import java.util.Scanner;

/**
 *
 * @author joan
 */
public class Usuario extends Jugador {
    
    //nombre en jugador
    private String password;
    
    public Usuario() {}
    
    public Usuario(boolean color, String nombre, String password) {
        super(color,nombre);
        this.password = password;
    }
       
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Pair getNextMove(Problema p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca coordenada origen, ex e4: ");
        String c1 = sc.next();
        sc.nextLine();
        System.out.println("Introduzca coordenada final, ex e4: ");
        String c2 = sc.next();
        sc.nextLine();
        Coordenada s1 = new Coordenada();
        s1.stringToCoord(c1);
        Coordenada s2 = new Coordenada();
        s2.stringToCoord(c2);
        Pair moves = new Pair(s1,s2);
        return moves;
    }
}
