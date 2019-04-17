package Dominio;
import java.util.*;
import Dominio.Usuario;

/**
 *
 * @author joan
 */
public class CtrlUsuarios {    
    private Map<String,String> Users = new HashMap<>();
    private String UserLogged;
    private String Guest;
    
    public CtrlUsuarios() {}
    
    public void registrarUsuario(String nom, String pass) {
        if (!existUser(nom)) {
            Users.put(nom, pass);
            new Usuario(nom,pass,false);
        }
        else System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
    }
    
    public void loginUsuario(String nom, String pass) {
        if (!Users.containsKey(nom)) System.out.println("El nombre de usuario es incorrecto");
        else {
            if (Users.get(nom).equals(pass)) UserLogged = nom;
            else System.out.println("La contraseña es incorrecta.");
        }
    }
    
    public void loginGuest(String nom, String pass) {
        if (!Users.containsKey(nom)) System.out.println("El nombre de usuario es incorrecto");
        else {
            if (Users.get(nom).equals(pass)) Guest = nom;
            else System.out.println("La contraseña es incorrecta.");
        }
    }
    
    public void logoutUsuario() {
        UserLogged = null;
    }
    
    public void logoutGuest() {
        Guest = null;
    }
    
    
//    public void modificarPassword(String nom, String newpassword) {
//        Usuario u = new Usuario();
//        u = consultarUsuario(nom);
//        if (u.correctPass(newpassword)) u.setPassword(newpassword);
//    }
    
//    public Usuario consultarUsuario(String nom) {
//        Usuario u = new Usuario();
//        Boolean find = false;
//        for (int i = 0; i < Users.size() && !find; i++) {
//            u = Users.get(i);
//            String n = u.getNombre();
//            if (nom.equals(n)) {
//                find = true;
//            }
//        }
//        return u;
//    }
    
    public Boolean existUser(String nom) {
        return Users.containsKey(nom);
    }
    
//    public void printUsuarios() {
//        for (int i = 0; i < Users.size(); i++) {
//            Usuario u = Users.get(i);
//            String n = u.getNombre();
//            String p = u.getPassword();
//            System.out.println(n);
//            System.out.println(p);
//        }
//    }
}
