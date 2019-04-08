/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;
import java.util.*;


/**
 *
 * @author joan
 */
public class Usuario {
    
    //Atributos
    
    private String nom;
    private String pass;
    private Boolean log;
    private ArrayList<Problema> ProblemasSuperados = new ArrayList();
    private ArrayList<Problema> ProblemasCreados = new ArrayList();
    
    //Constructoras
    
    public Usuario() {}
    
    public Usuario(String nombre, String password, Boolean login) {
        //Comprobar que el nombre de usuario existe???????????????????????????
        if (!correctPass(pass)) throw new IllegalArgumentException("La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
        nom = nombre;
        pass = password;
        log = login;
        listasUsuario = new CjtListado();
    }
    
    public String getNombre() {  
        return this.nom;
    }            
    public String getPassword() {
        return this.pass;
    }
    public Boolean isLog() {  
        return this.log;
    }
    public String setNombre() {  
        return this.nom;
    }            
    public String modificarPassword() {
        return this.pass;
    }
    public Boolean setLog() {  
        return this.log;
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
    
}
