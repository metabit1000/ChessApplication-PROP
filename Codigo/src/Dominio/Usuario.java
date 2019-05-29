package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import java.util.Scanner;

/**
 *
 * @author joan
 */
public class Usuario extends Jugador {
    private String nombre;
    private String password;
    
    public Usuario() {}
    
    public Usuario(boolean color, String nombre, String password) {
        super(color);
        this.nombre = nombre;
        this.password = password;
    }
    
     /**
     * pre:- 
     * post:Devolvemo el nombre del jugador
     * @return nombre
     */
    public String getNombre() {
        //devuelve el nombre del usuario
        return nombre;
    }
    
    /**
    * pre:-
    * post: Devolvera la contraseña del Usuario
    * @return 
    */
    public String getPassword() {
        // devulve la contraseña del usuario
        return this.password;
    }
    /**
     * pre:String password diferente a null
     * post:Pone la variable password como la password del atributo privado
     * @param password 
     */
    public void setPassword(String password) {
        //esta función pone la contraseña password a la contraseña del usuario
        this.password = password;
    }
}
