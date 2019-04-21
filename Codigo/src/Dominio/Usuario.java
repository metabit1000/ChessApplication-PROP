package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import java.util.Scanner;

/**
 *
 * @author joan
 */
public class Usuario extends Jugador {
    
    private String nom;
    private String pass;
    
    public Usuario() {}
    
    public Usuario(boolean color) {
        super(color);
    }
    
    public Usuario(String nombre, String password) {
        nom = nombre;
        pass = password;
    }
    
    public void setNombre(String nombre) {  
        nom = nombre;
    } 
    
    public String getNombre() {  
        return this.nom;
    }            
    public String getPassword() {
        return this.pass;
    }
    public void setPassword(String password) {
        pass = password;
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
