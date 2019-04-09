package Dominio;
import java.util.*;
import Dominio.Usuario;

/**
 *
 * @author joan
 */
public class Usuarios {
    
    private ArrayList<Usuario> Users = new ArrayList<Usuario>();
    
    public Usuarios() {}
    
    public void registrarUsuario(String nom, String pass) {
        if (!existUser(nom)) {
            Usuario u = new Usuario(nom,pass,false);
            Users.add(u);
        }
        else System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
    }
    
    public void loginUsuario(String nom, String pass) {
        Usuario u = new Usuario();
        u = consultarUsuario(nom);
        if (pass == u.getPassword()) u.setLog(true);
        else System.out.println("La contraseña es incorrecta.");
    }
    
    public void logoutUsuario(String nom) {
        Usuario u = new Usuario();
        u = consultarUsuario(nom);
        u.setLog(false);
    }
    
    public void modificarPassword(String nom, String newpassword) {
        Usuario u = new Usuario();
        u = consultarUsuario(nom);
        u.setPassword(newpassword);
    }
    
    public Usuario consultarUsuario (String nom) {
        Usuario u = new Usuario();
        Boolean find = false;
        for (int i = 0; i < Users.size() && !find; i++) {
            u = Users.get(i);
            String n = u.getNombre();
            if (nom == n) {
                find = true;
            }
        }
        if (!find) throw new IllegalArgumentException("No existe un usuario con ese nombre.");
        else return u;
    }
    
    public Boolean existUser(String nom) {
        for (int i = 0; i < Users.size(); i++) {
            Usuario u = Users.get(i);
            String n = u.getNombre();
            if (nom == n) return true;
        }
        return false;
    }
}
