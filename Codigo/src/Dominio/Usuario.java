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
        // devulve la contraseña del usuario
        return this.password;
    }
    
    public void setPassword(String password) {
        //esta función pone la contraseña password a la contraseña del usuario
        this.password = password;
    }
}
