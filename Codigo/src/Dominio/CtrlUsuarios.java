package Dominio;
import java.util.*;

/**
 *
 * @author joan
 */
public class CtrlUsuarios {    
    private final Map<String,String> Users = new HashMap<>();
    private String UserLogged;
    private String Guest;
    
    public CtrlUsuarios() {}
    
    public String getUserLogged() {
        if (UserLogged != null ) return UserLogged;
        else return "No hay usuario logeado";
    }
    
    public String getGuest() {
        if (Guest != null ) return Guest;
        else return "No hay guest disponible";
    }
    
    public void registrarUsuario(String nom, String pass) {
        if (!existUser(nom)) {
            if (correctPass(pass)) {
                Users.put(nom, pass);
                System.out.println("Usuario registrado correctamente.");
            }
            else System.out.println("La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
        }
        else System.out.println("El usuario con nombre " + nom + " ya existe. Prueba con otro.");
    }
    
    public void loginUsuario(String nom, String pass) {
        if (!Users.containsKey(nom)) System.out.println("El nombre de usuario es incorrecto");
        else {
            if (Users.get(nom).equals(pass)) {
                UserLogged = nom;
                System.out.println("Sesión iniciada satisfactoriamente");
            }
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
    
    public Boolean correctPass(String pass) {
        boolean n = false;
        boolean m = false;
        boolean M = false;
        for (int j = 0; j < pass.length(); ++j) {
            String mayu ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for(int i = 0; i < mayu.length(); ++i) {
                if (mayu.charAt(i) == pass.charAt(j)) M = true; // Es una letra mayuscula
            }
            String minu = "abcdefghijklmnopqrstuvwxyz";
            for(int i = 0; i < minu.length(); ++i) {
                if (minu.charAt(i) == pass.charAt(j)) m = true; // Es una letra minúscula
            }
            String num = "0123456789";
            for(int i = 0; i < num.length(); ++i)
                if (num.charAt(i) == pass.charAt(j)) n = true; // Es un número
            }
        return pass.length() > 5 && n && m && M;
    }
    
    public Boolean existUser(String nom) {
        return Users.containsKey(nom);
    }
    
    public void printUsuarios() {
        for(String key : Users.keySet()) {
            System.out.println(key);
        }
    }
}

