package Dominio;
import java.util.*;
import Dominio.Usuario;

/**
 *
 * @author joan
 */
public class CtrlUsuarios {
    
    private ArrayList<Usuario> Users = new ArrayList<Usuario>(); //hacer un map con key = nombre//valor = password
    
    public CtrlUsuarios() {}
    
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
        if (pass.equals(u.getPassword())) u.setLog(true);
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
        if (u.correctPass(newpassword)) u.setPassword(newpassword);
    }
    
    public Usuario consultarUsuario (String nom) {
        Usuario u = new Usuario();
        Boolean find = false;
        for (int i = 0; i < Users.size() && !find; i++) {
            u = Users.get(i);
            String n = u.getNombre();
            if (nom.equals(n)) {
                find = true;
            }
        }
        return u;
    }
    
    public Boolean existUser(String nom) {
        for (int i = 0; i < Users.size(); i++) {
            Usuario u = Users.get(i);
            String n = u.getNombre();
            if (nom.equals(n)) return true;
        }
        return false;
    }
    
    public void printUsuarios() {
        for (int i = 0; i < Users.size(); i++) {
            Usuario u = Users.get(i);
            String n = u.getNombre();
            String p = u.getPassword();
            System.out.println(n);
            System.out.println(p);
        }
    }
}
