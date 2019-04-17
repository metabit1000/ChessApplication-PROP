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
public class Usuario extends Jugador {
    
    //Atributos
    
    private String nom;
    private String pass;
    private ArrayList<String> ProblemasCreados;
    
    //Constructoras
    
    public Usuario() {}
    
    public Usuario(String nombre, String password, Boolean login) {
        //Comprobar que el nombre de usuario existe???????????????????????????
        //if (!correctPass(password)) System.out.println("La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
        nom = nombre;
        pass = password;
        //Añadir new ArrayLists de ProblemasSuperados y ProblemasCreados cuando tengamos la clase problemas
    }
    
    public String getNombre() {  
        return this.nom;
    }            
    public String getPassword() {
        return this.pass;
    }
    public void setPassword(String password) {
        //if (!correctPass(pass)) System.out.println("La contraseña necesita como mínimo 6 carácteres y tener como mínimo una letra minúscula, una mayúscula y un número.");
        pass = password;
    }
    
//    public Boolean correctPass(String pass) {//esta clase va aqui o en ConjuntoUsuarios??????????????????????
//        boolean n = false;
//        boolean m = false;
//        boolean M = false;
//        for (int j = 0; j < pass.length(); ++j) {
//            String mayu ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//            for(int i = 0; i < mayu.length(); ++i) {
//                if (mayu.charAt(i) == pass.charAt(j)) M = true; // Es una letra mayuscula
//            }
//            String minu = "abcdefghijklmnopqrstuvwxyz";
//            for(int i = 0; i < minu.length(); ++i) {
//                if (minu.charAt(i) == pass.charAt(j)) m = true; // Es una letra minúscula
//            }
//            String num = "0123456789";
//            for(int i = 0; i < num.length(); ++i)
//                if (num.charAt(i) == pass.charAt(j)) n = true; // Es un número
//            }
//        return pass.length() > 5 && n && m && M;
//    }
    
}
