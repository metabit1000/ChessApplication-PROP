package Dominio;

/**
 *
 * @author joan
 */
public class Usuario extends Jugador {
    
    private String nom;
    private String pass;

    public Usuario() {}
    
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
}
